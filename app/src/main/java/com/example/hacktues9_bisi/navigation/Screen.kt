package com.example.hacktues9_bisi.navigation

sealed class Screen(
    val route: String
) {
    object Login : Screen("login")
    object Registration : Screen("registration")
    object Home : Screen("home")
    object SingleCustomer : Screen ("single_customer")
}