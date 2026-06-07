package com.example.microapp.navigation

data class NavItem(val screen: Screen, val icon: String, val label: String)

val NAV_ITEMS = listOf(
    NavItem(Screen.Home, "\uD83C\uDFE0", "Home"),
    NavItem(Screen.Tasks, "⚡", "Tasks"),
    NavItem(Screen.Games, "\uD83C\uDFAE", "Games"),
    NavItem(Screen.Profile, "\uD83D\uDC64", "Me")
)
