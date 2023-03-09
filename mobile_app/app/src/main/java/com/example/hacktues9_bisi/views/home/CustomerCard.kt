package com.example.hacktues9_bisi.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hacktues9_bisi.R
import com.example.hacktues9_bisi.data.server.Customer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerCard(
    modifier: Modifier = Modifier,
    customer: Customer,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(bottom = 16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "customer card"
            )
            Spacer(modifier = modifier.height(16.dp))
            Row {
                Text(text = "Customer ID: ")
                Text(text = customer.id.toString())
            }
            Row {
                Text(text = "Customer risk: ")
                Text(text = "${customer.riskPercentage}%")
            }
        }
    }
}