package com.example.appproductos

import retrofit2.Call
import retrofit2.http.GET

interface ProductoService {
    @GET("api/productos")
    fun getProductos(): Call<List<Producto>>
}
