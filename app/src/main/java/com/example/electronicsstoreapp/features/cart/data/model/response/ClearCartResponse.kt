package com.example.electronicsstoreapp.features.cart.data.model.response

import com.google.gson.annotations.SerializedName

data class ClearCartResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
)
