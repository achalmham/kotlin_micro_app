package com.example.microapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BadgeData(val icon: String, val title: String, val ok: Boolean)
data class StatData(val icon: String, val value: String, val label: String)
data class ButtonData(val icon: String, val text: String)

@Composable
fun ProfileScreen() {
    val badges = listOf(
        BadgeData("🏅", "First Order", true),
        BadgeData("🔥", "3-Day", true),
        BadgeData("🔥", "7-Day", true),
        BadgeData("🤝", "First Ref", false),
        BadgeData("💎", "Elite", false),
        BadgeData("👑", "VaultKing", false)
    )
    
    val stats = listOf(
        StatData("🔥", "7 Days", "Streak"),
        StatData("👁️", "1,000", "Views"),
        StatData("⭐", "90", "RP"),
        StatData("🤝", "0", "Referrals")
    )
    
    val buttons = listOf(
        ButtonData("📲", "Telegram Bot Pe Jaao"),
        ButtonData("⚙️", "Settings")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 90.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "👤 My Vault",
            fontSize = 20.sp,
            fontWeight = FontWeight(900),
            color = C.white,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        
        // Profile Card
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            C.purple.copy(alpha = 0.27f),
                            C.card
                        )
                    )
                )
                .border(1.dp, C.purple.copy(alpha = 0.27f), RoundedCornerShape(24.dp))
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(C.purple, C.purpleL)
                        )
                    )
                    .border(3.dp, C.gold.copy(alpha = 0.27f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "RA",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(900),
                    color = Color.White
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = "Rahul",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(900),
                    color = C.white
                )
                Text(
                    text = "#VLT-00001 • Member since May 2025",
                    fontSize = 12.sp,
                    color = C.grey,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Tag(text = "🥉 Rookie Vaulter", color = C.gold)
            }
        }
        
        // Stats Grid
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            stats.chunked(2).forEach { rowStats ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowStats.forEach { stat ->
                        AppCard(
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stat.icon,
                                    fontSize = 26.sp,
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                                Text(
                                    text = stat.value,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight(900),
                                    color = C.white
                                )
                                Text(
                                    text = stat.label,
                                    fontSize = 11.sp,
                                    color = C.grey
                                )
                            }
                        }
                    }
                    if (rowStats.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
        
        // Achievements Title
        Text(
            text = "ACHIEVEMENTS",
            fontSize = 11.sp,
            fontWeight = FontWeight(700),
            color = C.grey,
            letterSpacing = 2.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        
        // Badges Grid
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            badges.chunked(3).forEach { rowBadges ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowBadges.forEach { b ->
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .alpha(if (b.ok) 1f else 0.4f)
                                .clip(RoundedCornerShape(14.dp))
                                .background(if (b.ok) C.gold.copy(alpha = 0.07f) else C.card)
                                .border(
                                    1.dp,
                                    if (b.ok) C.gold.copy(alpha = 0.27f) else Color.White.copy(alpha = 0.05f),
                                    RoundedCornerShape(14.dp)
                                )
                                .padding(vertical = 14.dp, horizontal = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = b.icon,
                                fontSize = 24.sp,
                                modifier = Modifier.padding(bottom = 6.dp)
                            )
                            Text(
                                text = b.title,
                                fontSize = 10.sp,
                                color = if (b.ok) C.gold else C.grey,
                                fontWeight = FontWeight(700)
                            )
                            if (!b.ok) {
                                Text(
                                    text = "🔒",
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }
                    if (rowBadges.size < 3) {
                        repeat(3 - rowBadges.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        
        // Bottom Buttons
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            buttons.forEach { btn ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(C.cardL)
                        .border(1.dp, Color.White.copy(alpha = 0.06f), RoundedCornerShape(14.dp))
                        .clickable { /* Handle click */ }
                        .padding(vertical = 14.dp, horizontal = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = btn.icon,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Text(
                        text = btn.text,
                        color = C.white,
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "→",
                        color = C.grey,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
