package com.example.appproductos

import com.example.appproductos.LoginResponse
import com.example.appproductos.RegistroDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST



interface AccountService {

    @POST("api/account/register")
    fun registro(@Body registerDto: RegistroDto): Call<String>

    @POST("api/account/login")
    fun login(@Body loginDto: LoginRequest): Call<LoginResponse>
}