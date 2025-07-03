package com.empresaficticia.SaludTotal.retrofitClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

        private const val BASE_URL = "http://10.0.2.2:5000/api/" // Para AVD
        // private const val BASE_URL = "http://192.168.0.5:5000/api/" // Para celular físico

        val instance: SaludTotalApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SaludTotalApi::class.java)
        }
}