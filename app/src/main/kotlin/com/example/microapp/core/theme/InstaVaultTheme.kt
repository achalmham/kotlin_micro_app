package com.example.microapp.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkScheme = darkColorScheme(
    primary = C.purple,
    secondary = C.gold,
    background = C.bg,
    surface = C.card,
    onPrimary = C.white,
    onSecondary = C.bg,
    onBackground = C.white,
    onSurface = C.white,
    error = C.red
)

@Composable
fun InstaVaultTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkScheme,
        content = content
    )
}
