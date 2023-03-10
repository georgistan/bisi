package com.example.hacktues9_bisi.repository

import com.example.hacktues9_bisi.data.server.Customer

interface CustomersRepository {
    suspend fun getCustomers(): List<Customer>
}