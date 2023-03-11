package com.example.hacktues9_bisi.data.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CustomerEntity(
    @field:Json(name = "created_at")
    var createdAt: String,

    @field:Json(name = "image_url")
    var imageUrl: String,

    @field:Json(name = "risk_percentage")
    var riskPercentage: Int,

    @field:Json(name = "uid")
    var id: String
)
