package com.example.microapp.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.microapp.core.components.AppCard
import com.example.microapp.core.theme.C
import com.example.microapp.data.model.ActivityItem

@Composable
fun RecentActivityList(activities: List<ActivityItem>) {
    Text(
        text = "Recent Activity",
        color = C.white,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 8.dp)
    )

    AppCard(contentPadding = PaddingValues(0.dp)) {
        activities.forEachIndexed { index, item ->
            ActivityRow(
                icon = item.icon,
                title = item.title,
                time = item.time,
                amount = item.amount,
                isNegative = item.isNegative
            )
            if (index < activities.size - 1) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.05f))
                )
            }
        }
    }
}

@Composable
private fun ActivityRow(
    icon: String,
    title: String,
    time: String,
    amount: String,
    isNegative: Boolean = false
) {
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
