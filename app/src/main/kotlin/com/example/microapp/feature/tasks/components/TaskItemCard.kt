package com.example.microapp.feature.tasks.components

import androidx.compose.animation.AnimatedVisibility
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
import com.example.microapp.core.components.Btn
import com.example.microapp.core.components.Tag
import com.example.microapp.core.theme.C
import com.example.microapp.data.model.Task

@Composable
fun TaskItemCard(
    task: Task,
    isSelected: Boolean,
    onSelect: () -> Unit,
    onStartTask: () -> Unit
) {
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
            .clickable { onSelect() }
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
                        Text(text = task.icon, fontSize = 20.sp)
                    }
                    Column(modifier = Modifier.padding(top = 4.dp)) {
                        Text(text = task.title, color = C.white, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text(text = task.desc, color = C.greyL, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp, bottom = 8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Tag(text = "⏱️ ${task.time}", color = C.greyL)
                            Tag(text = task.reward, color = C.gold)
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
                    Btn(text = "Start Task", onClick = onStartTask)
                }
            }
        }
    }
}
