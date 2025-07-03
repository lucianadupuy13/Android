package com.empresaficticia.SaludTotal.retrofitClient
import com.empresaficticia.SaludTotal.login.model.LoginRequest
import com.empresaficticia.SaludTotal.login.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SaludTotalApi {
//    @GET("cursos")
//    suspend fun obtenerCursos(): List<Curso>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}