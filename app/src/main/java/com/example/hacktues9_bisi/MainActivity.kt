package com.example.hacktues9_bisi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.hacktues9_bisi.navigation.Navigation
import com.example.hacktues9_bisi.ui.theme.Hacktues9bisiTheme

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hacktues9bisiTheme {
                val navController = rememberNavController()

                Navigation(navController = navController)
            }
        }
    }
}