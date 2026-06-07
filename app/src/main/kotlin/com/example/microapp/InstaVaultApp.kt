package com.example.microapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.microapp.core.theme.C
import com.example.microapp.feature.home.HomeScreen
import com.example.microapp.feature.tasks.TasksScreen
import com.example.microapp.feature.games.GamesScreen
import com.example.microapp.feature.games.quiz.QuizScreen
import com.example.microapp.feature.games.tap.TapGameScreen
import com.example.microapp.feature.games.spin.SpinWheelScreen
import com.example.microapp.feature.result.TaskSuccessScreen
import com.example.microapp.feature.profile.ProfileScreen
import com.example.microapp.navigation.BottomNavBar
import com.example.microapp.navigation.Screen

/**
 * Main App composable — matches JSX: export default function App()
 * Handles screen routing and bottom navigation.
 */
@Composable
fun InstaVaultApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    // Active nav calculation — delegates to companion method
    val activeScreen = Screen.activeNavFor(currentScreen.route)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(C.bg)
            .systemBarsPadding() // Prevents overlap with time/battery
    ) {
        // Screen content
        Box(modifier = Modifier.fillMaxSize()) {
            when (currentScreen) {
                Screen.Home -> HomeScreen(onNavigate = { currentScreen = it })
                Screen.Tasks -> TasksScreen(onNavigate = { currentScreen = it })
                Screen.Games -> GamesScreen(onNavigate = { currentScreen = it })
                Screen.Quiz -> QuizScreen(onNavigate = { currentScreen = it })
                Screen.TapGame -> TapGameScreen(onNavigate = { currentScreen = it })
                Screen.SpinWheel -> SpinWheelScreen(onNavigate = { currentScreen = it })
                Screen.TaskSuccess -> TaskSuccessScreen(onNavigate = { currentScreen = it })
                Screen.Profile -> ProfileScreen()
                else -> HomeScreen(onNavigate = { currentScreen = it })
            }
        }

        // Bottom Navigation — always visible, fixed at bottom
        BottomNavBar(
            activeScreen = activeScreen,
            onNavigate = { currentScreen = it },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
