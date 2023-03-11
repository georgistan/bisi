package com.example.hacktues9_bisi.navigation

sealed class Screen(
    val route: String
) {
    object Home : Screen("home")
}