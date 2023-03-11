package com.example.hacktues9_bisi.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hacktues9_bisi.R
import com.example.hacktues9_bisi.data.server.Customer

@Composable
fun CustomerCard(
    modifier: Modifier = Modifier,
    customer: Customer
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imagesList.random()),
                contentDescription = stringResource(id = R.string.customer_card)
            )
            Spacer(modifier = modifier.height(16.dp))
            Row {
                Text(text = "Customer ID: ")
                Text(text = customer.id)
            }
            Row {
                Text(text = "Customer risk: ")
                Text(text = "${customer.riskPercentage}%")
            }
        }
    }
}

val imagesList = listOf(
    R.drawable.img_1,
    R.drawable.img_2,
    R.drawable.img_3
)