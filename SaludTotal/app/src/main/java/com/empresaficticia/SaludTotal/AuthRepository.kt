package com.empresaficticia.SaludTotal

import com.empresaficticia.SaludTotal.login.model.LoginRequest
import com.empresaficticia.SaludTotal.register.model.RegisterRequest
import com.empresaficticia.SaludTotal.retrofitClient.RetrofitClient

class AuthRepository {
    private val api = RetrofitClient.instance

    suspend fun login(request: LoginRequest) = api.login(request)
    suspend fun register(request: RegisterRequest) = api.register(request)
}