package com.example.appproductos

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://10.0.2.2:7176/"

    private fun getRetrofit(context: Context): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val sharedPref = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val token = sharedPref.getString("jwt_token", null)

        val clientBuilder = OkHttpClient.Builder().addInterceptor(logging)
        if (!token.isNullOrEmpty()) {
            clientBuilder.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getAccountService(context: Context): AccountService {
        return getRetrofit(context).create(AccountService::class.java)
    }

    fun getProductoService(context: Context): ProductoService {
        return getRetrofit(context).create(ProductoService::class.java)
    }
}
