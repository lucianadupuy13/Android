package com.empresaficticia.SaludTotal.register.model

data class RegisterRequest(
    val name: String,
    val secondName: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)