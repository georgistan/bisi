package com.example.hacktues9_bisi.data.server

import retrofit2.http.GET

interface CustomerService {
    @GET("/users")
    suspend fun getCustomers(): ServiceResponse
}