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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // --- Header ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Brush.linearGradient(listOf(C.purple, C.purpleD))),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "RA", color = C.white, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Column {
                    Text(text = "Welcome back,", color = C.greyL, fontSize = 12.sp)
                    Text(text = "Rahul ⚡", color = C.white, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
            // Bell Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(C.card)
                    .border(1.dp, C.purple.copy(alpha = 0.3f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🔔", fontSize = 18.sp)
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (-2).dp, y = 2.dp)
                        .size(8.dp)
                        .background(C.red, CircleShape)
                )
            }
        }

        // --- Hero Balance Card ---
        AppCard(
            glow = "gold",
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(text = "Vault Balance", color = C.greyL, fontSize = 13.sp)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                    ) {
                        Text(text = "⚡", fontSize = 28.sp)
                        Text(text = "1,700", color = C.white, fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
                    }
                }
                Tag(text = "PRO ✦", color = C.gold)
            }
            
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Level 5 (Pro Earner)", color = C.white, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = "1700/2000", color = C.gold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            AppProgressBar(value = 1700, max = 2000, color = C.gold)
            Text(text = "300 more to next level!", color = C.greyL, fontSize = 11.sp, modifier = Modifier.padding(top = 8.dp))
        }

        // --- Daily Task Banner ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF2A0A18), Color(0xFF1A0510))))
                .border(1.dp, C.red.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                .clickable { onNavigate("tasks") }
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Text(text = "AAJKA TASK", color = C.red, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
                        Box(
                            modifier = Modifier
                                .background(C.red.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(text = "• LIVE", color = C.red, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Text(text = "Watch 1 Reel & get 150 ⚡", color = C.white, fontSize = 13.sp)
                }
                Box(
                    modifier = Modifier
                        .background(C.red, RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = "GO", color = C.white, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // --- Quick Actions Grid ---
        Text(text = "Quick Actions", color = C.white, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickActionCard(icon = "🎮", title = "Games", subtitle = "Play & Earn", onClick = { onNavigate("games") }, modifier = Modifier.weight(1f))
            QuickActionCard(icon = "🎡", title = "Spin Wheel", subtitle = "Daily Luck", onClick = { onNavigate("spin") }, modifier = Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickActionCard(icon = "🎁", title = "Daily Bonus", subtitle = "Claimed", onClick = { }, modifier = Modifier.weight(1f))
            QuickActionCard(icon = "🏆", title = "Leaderboard", subtitle = "Rank #42", onClick = { }, modifier = Modifier.weight(1f))
        }

        // --- Recent Activity ---
        Text(text = "Recent Activity", color = C.white, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp))
        
        AppCard(contentPadding = PaddingValues(0.dp)) {
            ActivityRow(icon = "🎮", title = "Tap Frenzy", time = "2 mins ago", amount = "+40 ⚡")
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.White.copy(alpha = 0.05f)))
            ActivityRow(icon = "✅", title = "Daily Login", time = "2 hours ago", amount = "+10 ⚡")
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.White.copy(alpha = 0.05f)))
            ActivityRow(icon = "🛍️", title = "Bought Theme", time = "Yesterday", amount = "-100 ⚡", isNegative = true)
        }
    }
}

@Composable
fun QuickActionCard(icon: String, title: String, subtitle: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
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

@Composable
fun ActivityRow(icon: String, title: String, time: String, amount: String, isNegative: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(C.bg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = icon, fontSize = 16.sp)
            }
            Column {
                Text(text = title, color = C.white, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = time, color = C.greyL, fontSize = 11.sp)
            }
        }
        Text(
            text = amount,
            color = if (isNegative) C.white else C.green,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
