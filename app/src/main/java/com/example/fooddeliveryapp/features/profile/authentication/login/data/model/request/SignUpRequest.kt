package com.example.fooddeliveryapp.features.profile.authentication.login.data.model.request

data class SignUpRequest(
    val email: String,
    val password: String,
    val name:String,
    val phone:String,
    val address:String
)