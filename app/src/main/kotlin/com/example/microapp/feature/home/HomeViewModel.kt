package com.example.microapp.feature.home

import androidx.lifecycle.ViewModel
import com.example.microapp.data.model.ActivityItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    data class UiState(
        val userName: String = "Rahul",
        val balance: Int = 1700,
        val level: Int = 5,
        val levelName: String = "Pro Earner",
        val maxBalance: Int = 2000,
        val notificationCount: Int = 1,
        val recentActivity: List<ActivityItem> = listOf(
            ActivityItem(icon = "🎮", title = "Tap Frenzy", time = "2 mins ago", amount = "+40 ⚡"),
            ActivityItem(icon = "✅", title = "Daily Login", time = "2 hours ago", amount = "+10 ⚡"),
            ActivityItem(icon = "🛍️", title = "Bought Theme", time = "Yesterday", amount = "-100 ⚡", isNegative = true)
        )
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
}
