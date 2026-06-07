package com.example.microapp.feature.games

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.microapp.core.theme.C
import com.example.microapp.data.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GamesViewModel : ViewModel() {

    data class UiState(
        val games: List<Game> = listOf(
            Game("🧠", "Quiz Master", "GK + Bollywood", "+30 Sparks", C.blue, "quiz"),
            Game("🎯", "Tap Frenzy", "30 sec challenge", "+20 Sparks", C.green, "tap"),
            Game("🎰", "Spin Wheel", "Daily free spin!", "Up to +500 Sparks", C.gold, "spin"),
            Game("🃏", "Card Match", "Memory game", "+40 Sparks", C.purple, "cards"),
            Game("🔢", "Math Rush", "Quick calculations", "+25 Sparks", Color(0xFFFF6B9D), "math"),
            Game("🔤", "Word Hunt", "Find hidden words", "+35 Sparks", Color(0xFF00CEC9), "words")
        )
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
}
