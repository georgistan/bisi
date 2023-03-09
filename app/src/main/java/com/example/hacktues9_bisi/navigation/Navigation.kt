package com.example.hacktues9_bisi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hacktues9_bisi.views.login.LoginScreen
import com.example.hacktues9_bisi.views.home.HomeScreen
import com.example.hacktues9_bisi.views.registration.RegistrationScreen
import com.example.hacktues9_bisi.views.single_card.SingleCardScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController
            )
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(
                navController = navController
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
//        composable(
//            route = Screen.SingleCustomer.route,
//            arguments = listOf(
//                navArgument("customerId") {
//                    type = NavType.IntType
//                }
//            )
//        ) {
//                navBackStackEntry ->
//            val customerId = navBackStackEntry.arguments
//                ?.getString("customerId")
//
//            SingleCardScreen(
//                navController = navController,
//                customer = customerId
//            )
//        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        )
        launchSingleTop = true
        restoreState = true
    }