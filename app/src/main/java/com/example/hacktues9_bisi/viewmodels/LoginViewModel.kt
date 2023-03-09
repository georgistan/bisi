package com.example.hacktues9_bisi.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

//@HiltViewModel
class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set


    fun updateUsername(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }
}