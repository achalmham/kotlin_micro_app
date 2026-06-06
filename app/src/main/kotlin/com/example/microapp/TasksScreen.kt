package com.example.microapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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

data class TaskItem(val id: Int, val icon: String, val title: String, val desc: String, val time: String, val reward: String)

@Composable
fun TasksScreen(onNavigate: (String) -> Unit) {
    var sel by remember { mutableStateOf<Int?>(null) }

    val tasks = listOf(
        TaskItem(1, "📱", "Watch Reel", "Like & comment on latest reel", "2 min", "150 ⚡"),
        TaskItem(2, "📝", "Survey", "Complete basic profile survey", "5 min", "500 ⚡"),
        TaskItem(3, "🤝", "Refer Friend", "Invite using your link", "10 min", "1000 ⚡"),
        TaskItem(4, "⭐️", "Rate App", "Give us 5 stars on Play Store", "1 min", "200 ⚡"),
        TaskItem(5, "🔄", "Daily Share", "Share app link on WhatsApp", "1 min", "100 ⚡")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Title
        Text(text = "Aajka Task", color = C.white, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)

        // Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(C.purple.copy(alpha = 0.15f))
                .border(1.dp, C.purple.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Koi bhi 1 task complete karo aur extra 100 ⚡ bonus paao!",
                color = C.purpleL,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }

        // Task List
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            tasks.forEach { t ->
                val isSelected = sel == t.id
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) C.cardL else C.card)
                        .border(
                            1.dp,
                            if (isSelected) C.purple else Color.White.copy(alpha = 0.05f),
                            RoundedCornerShape(16.dp)
                        )
                        .clickable { sel = if (isSelected) null else t.id }
                        .padding(16.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(C.bg, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = t.icon, fontSize = 20.sp)
                                }
                                Column(modifier = Modifier.padding(top = 4.dp)) {
                                    Text(text = t.title, color = C.white, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                                    Text(text = t.desc, color = C.greyL, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp, bottom = 8.dp))
                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        Tag(text = "⏱️ ${t.time}", color = C.greyL)
                                        Tag(text = t.reward, color = C.gold)
                                    }
                                }
                            }
                            // Checkbox/Radio indicator
                            Box(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .border(
                                        2.dp,
                                        if (isSelected) C.purple else C.grey,
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isSelected) {
                                    Box(modifier = Modifier.size(10.dp).background(C.purple, CircleShape))
                                }
                            }
                        }

                        // Expandable Start Button
                        AnimatedVisibility(visible = isSelected) {
                            Column {
                                Spacer(modifier = Modifier.height(16.dp))
                                Btn(text = "Start Task", onClick = { onNavigate("task_success") })
                            }
                        }
                    }
                }
            }
        }
    }
}
