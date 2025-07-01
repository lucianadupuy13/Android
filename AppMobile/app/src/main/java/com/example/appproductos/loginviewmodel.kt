package com.example.appproductos.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appproductos.LoginRequest
import com.example.appproductos.LoginResponse
import com.example.appproductos.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val loginResult = MutableLiveData<Result<LoginResponse>>()

    fun login(email: String, password: String) {
        val request = LoginRequest(email, password)

        RetrofitClient.apiService.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        loginResult.postValue(Result.success(it))
                    } ?: loginResult.postValue(Result.failure(Exception("Respuesta vacía")))
                } else {
                    loginResult.postValue(Result.failure(Exception("Credenciales inválidas")))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginResult.postValue(Result.failure(t))
            }
        })
    }
}
