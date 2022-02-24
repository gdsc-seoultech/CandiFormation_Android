package com.solution_challenge.candiformation.models

data class LoginResponse(
    val token: String,
    val username: String,
    val usernickname: String
)