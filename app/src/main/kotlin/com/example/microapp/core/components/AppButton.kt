package com.example.microapp.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.microapp.core.theme.C

// ─── Btn ───────────────────────────────────────────────────────
// Matches JSX: const Btn = ({ children, onClick, variant, style }) => ...
@Composable
fun Btn(
    text: String,
    onClick: () -> Unit,
    variant: String = "primary",
    modifier: Modifier = Modifier
) {
    val bgBrush: Brush
    val textColor: Color
    val borderColor: Color?

    when (variant) {
        "gold" -> {
            bgBrush = Brush.linearGradient(listOf(C.gold, C.goldL))
            textColor = C.bg
            borderColor = null
        }
        "ghost" -> {
            bgBrush = Brush.linearGradient(
                listOf(Color.White.copy(alpha = 0.04f), Color.White.copy(alpha = 0.04f))
            )
            textColor = C.greyL
            borderColor = Color.White.copy(alpha = 0.08f)
        }
        "danger" -> {
            bgBrush = Brush.linearGradient(
                listOf(C.red.copy(alpha = 0.13f), C.red.copy(alpha = 0.13f))
            )
            textColor = C.red
            borderColor = C.red.copy(alpha = 0.27f)
        }
        else -> {
            // primary — JSX had typo "liner-gradient", we use correct linear-gradient
            bgBrush = Brush.linearGradient(listOf(C.purple, C.purpleL))
            textColor = C.white
            borderColor = null
        }
    }

    val shape = RoundedCornerShape(14.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(bgBrush)
            .then(
                if (borderColor != null) Modifier.border(1.dp, borderColor, shape)
                else Modifier
            )
            .clickable(onClick = onClick)
            .padding(vertical = 13.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )
    }
}
