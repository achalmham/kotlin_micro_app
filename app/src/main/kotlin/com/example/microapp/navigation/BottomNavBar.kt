package com.example.microapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.microapp.core.theme.C

// ─── BottomNavBar ─────────────────────────────────────────────────
// Matches JSX: const BottomNav = ({ active, go }) => ...
@Composable
fun BottomNavBar(activeScreen: Screen, onNavigate: (Screen) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(C.bg.copy(alpha = 0.92f))
    ) {
        // Top border line (JSX: borderTop: "1px solid rgba(139,63,217,0.18)")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(C.purple.copy(alpha = 0.18f))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 18.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NAV_ITEMS.forEach { item ->
                val isActive = activeScreen == item.screen
                Column(
                    modifier = Modifier.clickable { onNavigate(item.screen) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(
                        text = item.icon,
                        fontSize = if (isActive) 26.sp else 22.sp
                    )
                    Text(
                        text = item.label,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isActive) C.gold else C.grey
                    )
                    if (isActive) {
                        Box(
                            modifier = Modifier
                                .size(4.dp)
                                .background(C.gold, CircleShape)
                        )
                    }
                }
            }
        }
    }
}
