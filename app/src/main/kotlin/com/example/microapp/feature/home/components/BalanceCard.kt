package com.example.microapp.feature.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.microapp.core.components.AppCard
import com.example.microapp.core.components.AppProgressBar
import com.example.microapp.core.components.Tag
import com.example.microapp.core.theme.C

@Composable
fun BalanceCard(
    balance: Int,
    level: Int,
    levelName: String,
    maxBalance: Int
) {
    AppCard(
        glow = "gold",
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.Top
        ) {
            Column {
                Text(text = "Vault Balance", color = C.greyL, fontSize = 13.sp)
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                ) {
                    Text(text = "⚡", fontSize = 28.sp)
                    Text(
                        text = "%,d".format(balance),
                        color = C.white,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
            Tag(text = "PRO ✦", color = C.gold)
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Level $level ($levelName)",
                color = C.white,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$balance/$maxBalance",
                color = C.gold,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
        AppProgressBar(value = balance, max = maxBalance, color = C.gold)
        Text(
            text = "${maxBalance - balance} more to next level!",
            color = C.greyL,
            fontSize = 11.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
