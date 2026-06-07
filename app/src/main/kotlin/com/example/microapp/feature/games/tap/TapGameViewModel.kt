package com.example.microapp.feature.games.tap

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.microapp.data.model.Spark
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.min
import kotlin.random.Random

class TapGameViewModel : ViewModel() {

    data class UiState(
        val score: Int = 0,
        val timeLeft: Int = 30,
        val isStarted: Boolean = false,
        val isDone: Boolean = false,
        val reward: Int = 0
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val sparks = mutableStateListOf<Spark>()
    private var idCounter = 0

    fun startGame() {
        _uiState.update { it.copy(isStarted = true) }
    }

    fun tapSpark(id: Int) {
        sparks.removeAll { it.id == id }
        _uiState.update {
            val newScore = it.score + 1
            it.copy(score = newScore, reward = min(newScore * 2, 20))
        }
    }

    fun spawnSpark() {
        idCounter++
        val newSpark = Spark(
            id = idCounter,
            x = Random.nextFloat() * 0.75f,
            y = Random.nextFloat() * 0.70f,
            size = 28f + Random.nextFloat() * 20f
        )
        if (sparks.size > 10) {
            sparks.removeAt(0)
        }
        sparks.add(newSpark)
    }

    fun decrementTimer() {
        _uiState.update {
            val newTime = it.timeLeft - 1
            if (newTime <= 0) {
                it.copy(timeLeft = 0, isDone = true, reward = min(it.score * 2, 20))
            } else {
                it.copy(timeLeft = newTime)
            }
        }
    }
}
