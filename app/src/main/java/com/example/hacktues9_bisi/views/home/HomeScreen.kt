package com.example.hacktues9_bisi.views.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.hacktues9_bisi.navigation.Screen
import com.example.hacktues9_bisi.navigation.navigateSingleTopTo
import com.example.hacktues9_bisi.viewmodels.LoginViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    HomeContent(
        onClickUserCard = { customerId ->
            navController.navigateToSingleCustomer(customerId)
        }
    )
}

private fun NavHostController.navigateToSingleCustomer(customerId: Int) {
    this.navigateSingleTopTo("${Screen.SingleCustomer.route}/$customerId")
}