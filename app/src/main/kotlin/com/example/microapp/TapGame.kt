package com.example.microapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import kotlin.math.min
import kotlin.random.Random

// ─── Data ──────────────────────────────────────────────────────
private data class Spark(val id: Int, val x: Float, val y: Float, val size: Float)

// ─── TapGame ───────────────────────────────────────────────────
@Composable
fun TapGame(onNavigate: (String) -> Unit) {
    val sparks = remember { mutableStateListOf<Spark>() }
    var score by remember { mutableIntStateOf(0) }
    var timeLeft by remember { mutableIntStateOf(30) }
    var started by remember { mutableStateOf(false) }
    var done by remember { mutableStateOf(false) }
    val idCounter = remember { mutableIntStateOf(0) }

    // Countdown timer
    LaunchedEffect(started, done) {
        if (!started || done) return@LaunchedEffect
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
        done = true
    }

    // Spark spawner every 700ms
    LaunchedEffect(started, done) {
        if (!started || done) return@LaunchedEffect
        while (!done) {
            delay(700)
            if (done) break
            idCounter.intValue++
            val newSpark = Spark(
                id = idCounter.intValue,
                x = Random.nextFloat() * 0.75f,
                y = Random.nextFloat() * 0.70f,
                size = 28f + Random.nextFloat() * 20f
            )
            // Keep max ~11 sparks on screen
            if (sparks.size > 10) {
                sparks.removeAt(0)
            }
            sparks.add(newSpark)
        }
    }

    val reward = min(score * 2, 20)

    // ── Result screen ──
    if (done) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 40.dp, bottom = 90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("🎯", fontSize = 60.sp, modifier = Modifier.padding(bottom = 12.dp))
            Text(
                "Time Up!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = C.white,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(
                "$score sparks tap kiye!",
                fontSize = 14.sp,
                color = C.grey,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            AppCard(
                glow = "green",
                contentPadding = PaddingValues(horizontal = 40.dp, vertical = 24.dp),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Text(
                    "+$reward",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black,
                    color = C.green,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    "⚡ Sparks Earned!",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = C.green,
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top bar: Back, Tag, Timer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "← Back",
                color = C.greyL,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onNavigate("games") }
            )
            Tag(text = "🎯 Tap Frenzy", color = C.green)
            Tag(text = "⏱️ ${timeLeft}s", color = if (timeLeft < 10) C.red else C.greyL)
        }

        // Score cards row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AppCard(contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)) {
                Text(
                    "$score",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    color = C.gold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    "Tapped",
                    fontSize = 11.sp,
                    color = C.grey,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            AppCard(contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp)) {
                Text(
                    "+$reward",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    color = C.green,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    "Sparks",
                    fontSize = 11.sp,
                    color = C.grey,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        if (!started) {
            // Start button centered
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Btn(
                    text = "🎯 Start Game!",
                    onClick = { started = true },
                    variant = "gold",
                    modifier = Modifier.width(180.dp)
                )
            }
        } else {
            // Game area
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(C.cardL)
                    .border(1.dp, C.green.copy(alpha = 0.15f), RoundedCornerShape(20.dp))
            ) {
                // Background watermark ⚡
                Text(
                    "⚡",
                    fontSize = 80.sp,
                    color = Color.White.copy(alpha = 0.04f),
                    modifier = Modifier.align(Alignment.Center)
                )

                // Sparks
                sparks.forEach { s ->
                    Text(
                        "⚡",
                        fontSize = s.size.sp,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.TopStart)
                            .offset(
                                x = (s.x * 280).dp,
                                y = (s.y * 400).dp
                            )
                            .clickable {
                                sparks.removeAll { it.id == s.id }
                                score++
                            }
                    )
                }

                // Bottom label
                Text(
                    "TAP THE SPARKS!",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White.copy(alpha = 0.3f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
