package com.example.hacktues9_bisi.views.single_card

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.hacktues9_bisi.network.customers.Customer
import com.example.hacktues9_bisi.navigation.Screen
import com.example.hacktues9_bisi.navigation.navigateSingleTopTo

@Composable
fun SingleCardScreen(
    navController: NavHostController,
    customer: Customer
) {
    SingleCardContent(
        customer = customer,
        onClickBackButton = {
            navController.navigateSingleTopTo(Screen.Home.route)
        }
    )
}