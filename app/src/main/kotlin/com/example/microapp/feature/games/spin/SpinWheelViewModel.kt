package com.example.microapp.feature.games.spin

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.microapp.data.model.WheelSegment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SpinWheelViewModel : ViewModel() {

    data class UiState(
        val isSpinning: Boolean = false,
        val result: WheelSegment? = null,
        val targetAngle: Float = 0f,
        val segments: List<WheelSegment> = listOf(
            WheelSegment("30 Sparks", Color(0xFF4ECDC4), 30),
            WheelSegment("75 Sparks", Color(0xFF45B7D1), 75),
            WheelSegment("50 Sparks", Color(0xFF96CEB4), 50),
            WheelSegment("500 Sparks!", Color(0xFFF5A623), 500),
            WheelSegment("100 Sparks", Color(0xFFDDA0DD), 100),
            WheelSegment("25 Sparks", Color(0xFF98D8C8), 25),
            WheelSegment("200 Sparks", Color(0xFFF7DC6F), 200),
            WheelSegment("150 Sparks", Color(0xFFBB8FCE), 150)
        )
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun spin() {
        val state = _uiState.value
        if (state.isSpinning || state.result != null) return

        val extra = 1440f + (Math.random() * 720f).toFloat()
        val finalAngle = state.targetAngle + extra

        _uiState.update {
            it.copy(isSpinning = true, targetAngle = finalAngle)
        }
    }

    fun onSpinComplete() {
        val state = _uiState.value
        val segAngle = 360f / state.segments.size
        val normalized = ((state.targetAngle % 360f) + 360f) % 360f
        val segIndex = ((360f - normalized) / segAngle).toInt() % state.segments.size
        _uiState.update {
            it.copy(isSpinning = false, result = it.segments[segIndex])
        }
    }
}
