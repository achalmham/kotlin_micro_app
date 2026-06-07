package com.example.microapp.feature.games.quiz

import androidx.lifecycle.ViewModel
import com.example.microapp.data.model.QuizQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {

    data class UiState(
        val currentIndex: Int = 0,
        val selectedAnswer: Int? = null,
        val score: Int = 0,
        val isDone: Boolean = false,
        val timeLeft: Int = 15,
        val questions: List<QuizQuestion> = listOf(
            QuizQuestion("India ki capital kya hai?", listOf("Mumbai", "New Delhi", "Chennai", "Kolkata"), 1),
            QuizQuestion("Bharat ka rashtriya khel kaun sa hai?", listOf("Cricket", "Football", "Hockey", "Kabaddi"), 2),
            QuizQuestion("Taj Mahal kahan hai?", listOf("Delhi", "Jaipur", "Agra", "Lucknow"), 2),
            QuizQuestion("1 + 1 × 0 + 1 = ?", listOf("0", "1", "2", "3"), 2),
            QuizQuestion("Instagram kis company ka hai?", listOf("Google", "Twitter", "Meta", "Apple"), 2)
        )
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun selectAnswer(index: Int) {
        val state = _uiState.value
        if (state.selectedAnswer != null) return
        val isCorrect = index == state.questions[state.currentIndex].correctIndex
        _uiState.update {
            it.copy(
                selectedAnswer = index,
                score = if (isCorrect) it.score + 1 else it.score
            )
        }
    }

    fun advanceQuestion() {
        val state = _uiState.value
        if (state.currentIndex + 1 >= state.questions.size) {
            _uiState.update { it.copy(isDone = true) }
        } else {
            _uiState.update {
                it.copy(
                    currentIndex = it.currentIndex + 1,
                    selectedAnswer = null,
                    timeLeft = 15
                )
            }
        }
    }

    fun decrementTimer() {
        _uiState.update { it.copy(timeLeft = it.timeLeft - 1) }
    }

    fun resetQuiz() {
        _uiState.value = UiState()
    }
}
