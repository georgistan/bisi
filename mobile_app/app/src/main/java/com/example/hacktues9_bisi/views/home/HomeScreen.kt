package com.example.hacktues9_bisi.views.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.hacktues9_bisi.navigation.Screen
import com.example.hacktues9_bisi.navigation.navigateSingleTopTo
import com.example.hacktues9_bisi.viewmodels.CustomersViewModel

@Composable
fun HomeScreen(
    viewModel: CustomersViewModel = hiltViewModel()
) {
    val customersList = viewModel.customers.collectAsState()

    HomeContent(
        customers = customersList.value
    )
}