package com.example.microapp.feature.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.microapp.core.components.AppCard
import com.example.microapp.core.theme.C
import com.example.microapp.data.model.UserStat

@Composable
fun StatsGrid(stats: List<UserStat>) {
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
}
