package com.example.microapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class GameData(
    val icon: String,
    val title: String,
    val sub: String,
    val reward: String,
    val color: Color,
    val id: String
)

@Composable
fun GamesScreen(onNavigate: (String) -> Unit) {
    val games = listOf(
        GameData("🧠", "Quiz Master", "GK + Bollywood", "+30 Sparks", C.blue, "quiz"),
        GameData("🎯", "Tap Frenzy", "30 sec challenge", "+20 Sparks", C.green, "tap"),
        GameData("🎰", "Spin Wheel", "Daily free spin!", "Up to +500 Sparks", C.gold, "spin"),
        GameData("🃏", "Card Match", "Memory game", "+40 Sparks", C.purple, "cards"),
        GameData("🔢", "Math Rush", "Quick calculations", "+25 Sparks", Color(0xFFFF6B9D), "math"),
        GameData("🔤", "Word Hunt", "Find hidden words", "+35 Sparks", Color(0xFF00CEC9), "words")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 90.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "🎮 Games",
            fontSize = 20.sp,
            fontWeight = FontWeight(900),
            color = C.white,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Task ke baad bonus Sparks kamao!",
            fontSize = 12.sp,
            color = C.grey,
            modifier = Modifier.padding(bottom = 18.dp)
        )
        
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            games.chunked(2).forEach { rowGames ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowGames.forEach { g ->
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(
                                            g.color.copy(alpha = 0.09f),
                                            C.card
                                        )
                                    )
                                )
                                .border(1.dp, g.color.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
                                .clickable { onNavigate(g.id) }
                                .padding(vertical = 18.dp, horizontal = 14.dp)
                        ) {
                            Text(
                                text = g.icon,
                                fontSize = 34.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Text(
                                text = g.title,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(800),
                                color = C.white,
                                modifier = Modifier.padding(bottom = 3.dp)
                            )
                            Text(
                                text = g.sub,
                                fontSize = 11.sp,
                                color = C.grey,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .background(g.color.copy(alpha = 0.13f), RoundedCornerShape(8.dp))
                                    .border(1.dp, g.color.copy(alpha = 0.27f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = g.reward,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight(700),
                                    color = g.color
                                )
                            }
                        }
                    }
                    if (rowGames.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
