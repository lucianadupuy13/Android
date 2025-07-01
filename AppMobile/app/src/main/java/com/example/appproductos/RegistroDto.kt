package com.example.appproductos


data class RegistroDto(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val name: String,
    val secondName: String
)