package com.example.microapp.feature.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.microapp.core.components.Tag
import com.example.microapp.core.theme.C

@Composable
fun ProfileHeader(
    userName: String,
    userId: String,
    memberSince: String,
    rank: String
) {
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
                text = userName,
                fontSize = 20.sp,
                fontWeight = FontWeight(900),
                color = C.white
            )
            Text(
                text = "$userId • Member since $memberSince",
                fontSize = 12.sp,
                color = C.grey,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Tag(text = rank, color = C.gold)
        }
    }
}
