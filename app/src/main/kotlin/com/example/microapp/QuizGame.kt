package com.example.microapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

// ─── Data ──────────────────────────────────────────────────────
private data class QuizQuestion(
    val q: String,
    val opts: List<String>,
    val ans: Int
)

private val quizQuestions = listOf(
    QuizQuestion("India ki capital kya hai?", listOf("Mumbai", "New Delhi", "Chennai", "Kolkata"), 1),
    QuizQuestion("Bharat ka rashtriya khel kaun sa hai?", listOf("Cricket", "Football", "Hockey", "Kabaddi"), 2),
    QuizQuestion("Taj Mahal kahan hai?", listOf("Delhi", "Jaipur", "Agra", "Lucknow"), 2),
    QuizQuestion("1 + 1 × 0 + 1 = ?", listOf("0", "1", "2", "3"), 2),
    QuizQuestion("Instagram kis company ka hai?", listOf("Google", "Twitter", "Meta", "Apple"), 2),
)

// ─── QuizGame ──────────────────────────────────────────────────
@Composable
fun QuizGame(onNavigate: (String) -> Unit) {
    var cur by remember { mutableIntStateOf(0) }
    var selected by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var done by remember { mutableStateOf(false) }
    var time by remember { mutableIntStateOf(15) }

    // Timer: counts down every second; auto-advances when 0
    LaunchedEffect(cur, done, selected) {
        if (done || selected != null) return@LaunchedEffect
        time = 15
        while (time > 0 && !done && selected == null) {
            delay(1000)
            time--
        }
        if (time <= 0 && selected == null && !done) {
            // auto-advance
            if (cur + 1 >= quizQuestions.size) {
                done = true
            } else {
                cur++
                selected = null
            }
        }
    }

    // Auto-advance after answer selection
    LaunchedEffect(selected) {
        if (selected == null) return@LaunchedEffect
        delay(800)
        if (cur + 1 >= quizQuestions.size) {
            done = true
        } else {
            cur++
            selected = null
        }
    }

    // ── Result screen ──
    if (done) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 40.dp, bottom = 90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🧠", fontSize = 60.sp, modifier = Modifier.padding(bottom = 12.dp))
            Text(
                "Quiz Complete!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = C.white,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(
                "$score/5 sahi jawab",
                fontSize = 14.sp,
                color = C.grey,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            AppCard(
                glow = "gold",
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 24.dp),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Text(
                    "+${score * 6}",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    color = C.gold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    "⚡ Sparks Earned!",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = C.gold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Btn(
                text = "← Games Pe Wapas",
                onClick = { onNavigate("games") },
                variant = "primary"
            )
        }
        return
    }

    // ── Game screen ──
    val q = quizQuestions[cur]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 90.dp)
    ) {
        // Top bar: Back, Tag, Timer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "← Back",
                color = C.greyL,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onNavigate("games") }
            )
            Tag(text = "🧠 Quiz Master", color = C.blue)
            Tag(text = "⏱️ ${time}s", color = if (time < 6) C.red else C.greyL)
        }

        // Progress bar + counter
        Column(modifier = Modifier.padding(bottom = 20.dp)) {
            AppProgressBar(value = cur, max = quizQuestions.size, color = C.blue)
            Text(
                "${cur + 1} / ${quizQuestions.size}",
                fontSize = 11.sp,
                color = C.grey,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }

        // Question card
        AppCard(
            contentPadding = PaddingValues(22.dp),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Text(
                q.q,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = C.white,
                lineHeight = 24.sp
            )
        }

        // Options
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            q.opts.forEachIndexed { i, option ->
                val bg: Color
                val borderColor: Color
                val textColor: Color

                if (selected != null) {
                    when {
                        i == q.ans -> {
                            bg = C.green.copy(alpha = 0.13f)
                            borderColor = C.green.copy(alpha = 0.53f)
                            textColor = C.green
                        }
                        i == selected && selected != q.ans -> {
                            bg = C.red.copy(alpha = 0.13f)
                            borderColor = C.red.copy(alpha = 0.53f)
                            textColor = C.red
                        }
                        else -> {
                            bg = C.cardL
                            borderColor = Color.White.copy(alpha = 0.08f)
                            textColor = C.white
                        }
                    }
                } else {
                    bg = C.cardL
                    borderColor = Color.White.copy(alpha = 0.08f)
                    textColor = C.white
                }

                val letter = ('A' + i)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(bg)
                        .border(1.5.dp, borderColor, RoundedCornerShape(14.dp))
                        .clickable {
                            if (selected == null) {
                                selected = i
                                if (i == q.ans) score++
                            }
                        }
                        .padding(horizontal = 18.dp, vertical = 14.dp)
                ) {
                    Text(
                        "$letter. $option",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
