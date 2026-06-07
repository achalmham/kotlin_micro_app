package com.example.microapp.feature.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.microapp.core.theme.C
import com.example.microapp.data.model.Badge

@Composable
fun AchievementGrid(badges: List<Badge>) {
    Text(
        text = "ACHIEVEMENTS",
        fontSize = 11.sp,
        fontWeight = FontWeight(700),
        color = C.grey,
        letterSpacing = 2.sp,
        modifier = Modifier.padding(bottom = 10.dp)
    )
    
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
                            .alpha(if (b.unlocked) 1f else 0.4f)
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (b.unlocked) C.gold.copy(alpha = 0.07f) else C.card)
                            .border(
                                1.dp,
                                if (b.unlocked) C.gold.copy(alpha = 0.27f) else Color.White.copy(alpha = 0.05f),
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
                            color = if (b.unlocked) C.gold else C.grey,
                            fontWeight = FontWeight(700)
                        )
                        if (!b.unlocked) {
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
}
