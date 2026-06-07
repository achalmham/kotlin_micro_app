package com.example.microapp.feature.profile

import androidx.lifecycle.ViewModel
import com.example.microapp.data.model.Badge
import com.example.microapp.data.model.ButtonData
import com.example.microapp.data.model.UserStat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    data class UiState(
        val userName: String = "Rahul",
        val userId: String = "#VLT-00001",
        val memberSince: String = "May 2025",
        val rank: String = "🥉 Rookie Vaulter",
        val stats: List<UserStat> = listOf(
            UserStat("🔥", "7 Days", "Streak"),
            UserStat("👁️", "1,000", "Views"),
            UserStat("⭐", "90", "RP"),
            UserStat("🤝", "0", "Referrals")
        ),
        val badges: List<Badge> = listOf(
            Badge("🏅", "First Order", true),
            Badge("🔥", "3-Day", true),
            Badge("🔥", "7-Day", true),
            Badge("🤝", "First Ref", false),
            Badge("💎", "Elite", false),
            Badge("👑", "VaultKing", false)
        ),
        val buttons: List<ButtonData> = listOf(
            ButtonData("📲", "Telegram Bot Pe Jaao"),
            ButtonData("⚙️", "Settings")
        )
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
}
