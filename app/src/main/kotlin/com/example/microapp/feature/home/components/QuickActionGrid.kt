package com.example.microapp.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.microapp.core.theme.C
import com.example.microapp.navigation.Screen

@Composable
fun QuickActionGrid(onNavigate: (Screen) -> Unit) {
    Text(
        text = "Quick Actions",
        color = C.white,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 8.dp)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuickActionCard(
            icon = "🎮",
            title = "Games",
            subtitle = "Play & Earn",
            onClick = { onNavigate(Screen.Games) },
            modifier = Modifier.weight(1f)
        )
        QuickActionCard(
            icon = "🎡",
            title = "Spin Wheel",
            subtitle = "Daily Luck",
            onClick = { onNavigate(Screen.SpinWheel) },
            modifier = Modifier.weight(1f)
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuickActionCard(
            icon = "🎁",
            title = "Daily Bonus",
            subtitle = "Claimed",
            onClick = { },
            modifier = Modifier.weight(1f)
        )
        QuickActionCard(
            icon = "🏆",
            title = "Leaderboard",
            subtitle = "Rank #42",
            onClick = { },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun QuickActionCard(
    icon: String,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(C.cardL)
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(C.bg, CircleShape)
                    .border(1.dp, Color.White.copy(alpha = 0.05f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = icon, fontSize = 20.sp)
            }
            Column {
                Text(text = title, color = C.white, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = subtitle, color = C.greyL, fontSize = 11.sp)
            }
        }
    }
}
