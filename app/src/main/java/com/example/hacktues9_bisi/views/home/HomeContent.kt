package com.example.hacktues9_bisi.views.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hacktues9_bisi.network.customers.Customer

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onClickUserCard: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(itemsData) { item ->
            CustomerCard(
                customer = item,
                onClick = {

                }
            )
        }
    }
}

private val itemsData = listOf(
    Customer(
        id = 1,
        riskPercentage = 60
    ),
    Customer(
        id = 2,
        riskPercentage = 70
    ),
    Customer(
        id = 3,
        riskPercentage = 50
    )
)