package com.example.microapp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

// ─── AppCard ───────────────────────────────────────────────────
// Matches JSX: const Card = ({ children, glow, style }) => ...
@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    glow: String = "",
    onClick: (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    val borderColor = when (glow) {
        "gold" -> C.gold.copy(alpha = 0.2f)
        "green" -> C.green.copy(alpha = 0.2f)
        else -> C.purple.copy(alpha = 0.2f)
    }
    val shape = RoundedCornerShape(20.dp)

    Column(
        modifier = modifier
            .clip(shape)
            .background(Brush.linearGradient(listOf(C.cardL, C.card)))
            .border(1.dp, borderColor, shape)
            .then(
                if (onClick != null) Modifier.clickable(onClick = onClick)
                else Modifier
            )
            .padding(contentPadding),
        content = content
    )
}

// ─── Tag ───────────────────────────────────────────────────────
// Matches JSX: const Tag = ({ children, color }) => ...
@Composable
fun Tag(text: String, color: Color = C.purple) {
    Text(
        text = text,
        color = color,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(color.copy(alpha = 0.13f), RoundedCornerShape(20.dp))
            .border(1.dp, color.copy(alpha = 0.27f), RoundedCornerShape(20.dp))
            .padding(horizontal = 10.dp, vertical = 3.dp)
    )
}

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

// ─── BottomNav ─────────────────────────────────────────────────
// Matches JSX: const BottomNav = ({ active, go }) => ...
data class NavItem(val id: String, val icon: String, val label: String)

val NAV_ITEMS = listOf(
    NavItem("home", "🏠", "Home"),
    NavItem("tasks", "⚡", "Tasks"),
    NavItem("games", "🎮", "Games"),
    NavItem("profile", "👤", "Me"),
)

@Composable
fun BottomNav(active: String, onNavigate: (String) -> Unit, modifier: Modifier = Modifier) {
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
                val isActive = active == item.id
                Column(
                    modifier = Modifier.clickable { onNavigate(item.id) },
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
