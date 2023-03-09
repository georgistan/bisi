package com.example.hacktues9_bisi.views.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.hacktues9_bisi.navigation.Screen
import com.example.hacktues9_bisi.navigation.navigateSingleTopTo
import com.example.hacktues9_bisi.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    LoginContent(
        onClickRegister = {
            navController.navigateSingleTopTo(Screen.Registration.route)
        }
    )
}

