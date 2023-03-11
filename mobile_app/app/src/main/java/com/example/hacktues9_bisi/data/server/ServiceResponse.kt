package com.example.hacktues9_bisi.data.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServiceResponse(
    @field:Json(name = "users")
    var customers: List<CustomerEntity> = listOf()
)
