package com.example.hacktues9_bisi.views.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hacktues9_bisi.data.server.Customer

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
        id = 1F,
        riskPercentage = 60F
    ),
    Customer(
        id = 2F,
        riskPercentage = 70F
    ),
    Customer(
        id = 3F,
        riskPercentage = 50F
    )
)