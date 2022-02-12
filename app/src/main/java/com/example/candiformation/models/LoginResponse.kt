package com.example.candiformation.models

data class LoginResponse(
    val token: String,
    val username: String,
    val usernickname: String
)