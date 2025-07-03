package com.empresaficticia.SaludTotal.retrofitClient
import com.empresaficticia.SaludTotal.login.model.LoginRequest
import com.empresaficticia.SaludTotal.login.model.LoginResponse
import com.empresaficticia.SaludTotal.register.model.RegisterRequest
import com.empresaficticia.SaludTotal.register.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/account/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/account/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}
