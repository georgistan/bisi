package com.example.hacktues9_bisi.views.single_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.example.hacktues9_bisi.R
import com.example.hacktues9_bisi.data.server.Customer

@Composable
fun SingleCardContent(
    modifier: Modifier = Modifier,
    customer: Customer,
    onClickBackButton: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(onClick = onClickBackButton) {
            Icon(
                imageVector = Icons.Default.ArrowBack, 
                contentDescription = "back to home"
            )
        }
        Image(painter = painterResource(id = R.drawable.img), contentDescription = "customer photo")
    }
}