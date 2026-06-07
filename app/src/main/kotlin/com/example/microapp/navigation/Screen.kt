package com.example.microapp.navigation

sealed interface Screen {
    val route: String

    data object Home : Screen { override val route = "home" }
    data object Tasks : Screen { override val route = "tasks" }
    data object Games : Screen { override val route = "games" }
    data object Profile : Screen { override val route = "profile" }
    data object Quiz : Screen { override val route = "quiz" }
    data object TapGame : Screen { override val route = "tap" }
    data object SpinWheel : Screen { override val route = "spin" }
    data object TaskSuccess : Screen { override val route = "task_success" }
    data object CardMatch : Screen { override val route = "cards" }
    data object MathRush : Screen { override val route = "math" }
    data object WordHunt : Screen { override val route = "words" }

    companion object {
        val bottomNavScreens = listOf(Home, Tasks, Games, Profile)

        fun activeNavFor(route: String?): Screen {
            return when {
                route == null -> Home
                bottomNavScreens.any { it.route == route } -> bottomNavScreens.first { it.route == route }
                route == TaskSuccess.route -> Tasks
                route in listOf(Quiz.route, TapGame.route, SpinWheel.route, CardMatch.route, MathRush.route, WordHunt.route) -> Games
                else -> Home
            }
        }
    }
}
