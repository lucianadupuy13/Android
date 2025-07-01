package com.example.appproductos

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // LOGIN
    @POST("api/account/login")
    fun login(@Body loginDto: LoginRequest): Call<LoginResponse>

    @POST("api/account/register")
    fun register(@Body registroDto: RegistroDto): Call<RegisterResponse>

    // LIST PRODUCTS
    @GET("api/productos")
    fun getProductos(@Query("page") page: Int): Call<List<Producto>>

    // PRODUCT DETAIL
    @GET("api/productos/{id}")
    fun getProductoDetail(@Path("id") id: Int): Call<Producto>

    // CREATE PRODUCT
    @POST("api/productos")
    fun createProducto(@Body producto: Producto): Call<Producto>

    // EDIT PRODUCT
    @PUT("api/productos/{id}")
    fun editProducto(@Path("id") id: Int, @Body producto: Producto): Call<Producto>

    // GET QR (string, suponiendo que devuelve URL del QR o texto QR)
    @GET("api/productos/{id}/qr")
    fun getProductoQr(@Path("id") id: Int): Call<QrResponse>
}
