package com.example.microapp.core.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.microapp.core.theme.C

// ─── AppProgressBar ────────────────────────────────────────────
// Matches JSX: const ProgressBar = ({ value, max, color }) => ...
@Composable
fun AppProgressBar(value: Int, max: Int, color: Color = C.gold) {
    val fraction = if (max > 0) (value.toFloat() / max).coerceIn(0f, 1f) else 0f
    val animatedFraction by animateFloatAsState(
        targetValue = fraction,
        animationSpec = tween(400),
        label = "progress"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White.copy(alpha = 0.07f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(animatedFraction)
                .clip(RoundedCornerShape(5.dp))
                .background(Brush.horizontalGradient(listOf(color, color.copy(alpha = 0.67f))))
        )
    }
}
