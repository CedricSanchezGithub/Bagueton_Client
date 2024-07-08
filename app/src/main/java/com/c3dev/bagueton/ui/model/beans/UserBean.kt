package com.c3dev.bagueton.ui.model.beans

data class User(
    val username: String,
    val password: String,
    val role: String = "ROLE_USER"
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class ApiResponse(
    val message: String,
    val token: String?
)
