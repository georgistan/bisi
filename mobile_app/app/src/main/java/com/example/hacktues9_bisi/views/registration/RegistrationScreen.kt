package com.example.hacktues9_bisi.views.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.hacktues9_bisi.navigation.Screen
import com.example.hacktues9_bisi.navigation.navigateSingleTopTo
import com.example.hacktues9_bisi.viewmodels.LoginViewModel

@Composable
fun RegistrationScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    RegistrationContent(
        onClickDone = {
            navController.navigateSingleTopTo(Screen.Login.route)
        }
    )
}