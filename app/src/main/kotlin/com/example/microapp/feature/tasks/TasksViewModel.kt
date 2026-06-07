package com.example.microapp.feature.tasks

import androidx.lifecycle.ViewModel
import com.example.microapp.data.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TasksViewModel : ViewModel() {

    data class UiState(
        val tasks: List<Task> = listOf(
            Task(1, "📱", "Watch Reel", "Like & comment on latest reel", "2 min", "150 ⚡"),
            Task(2, "📝", "Survey", "Complete basic profile survey", "5 min", "500 ⚡"),
            Task(3, "🤝", "Refer Friend", "Invite using your link", "10 min", "1000 ⚡"),
            Task(4, "⭐️", "Rate App", "Give us 5 stars on Play Store", "1 min", "200 ⚡"),
            Task(5, "🔄", "Daily Share", "Share app link on WhatsApp", "1 min", "100 ⚡")
        ),
        val selectedTaskId: Int? = null
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun selectTask(id: Int) {
        _uiState.update { state ->
            state.copy(selectedTaskId = if (state.selectedTaskId == id) null else id)
        }
    }

    fun clearSelection() {
        _uiState.update { it.copy(selectedTaskId = null) }
    }
}
