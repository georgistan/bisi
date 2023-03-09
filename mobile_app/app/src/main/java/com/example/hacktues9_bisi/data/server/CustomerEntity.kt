package com.example.hacktues9_bisi.data.server

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CustomerEntity(
    @field:Json(name = "id")
    var id: Float,

    @field:Json(name = "risk")
    var riskPercentage: Float,

//    @field:Json(name = "photo")
//    var photo: Any?
)
