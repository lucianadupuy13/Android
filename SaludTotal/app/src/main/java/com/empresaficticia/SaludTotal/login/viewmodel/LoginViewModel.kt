package com.empresaficticia.SaludTotal.login.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.empresaficticia.SaludTotal.login.model.LoginRequest
import com.empresaficticia.SaludTotal.login.model.LoginResponse
import com.empresaficticia.SaludTotal.retrofitClient.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Response


class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val loginResult = MutableLiveData<LoginResponse?>()
    val errorMessage = MutableLiveData<String?>()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response: Response<LoginResponse> =
                    RetrofitClient.instance.login(LoginRequest(email, password))

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResponse?.let {
                        loginResult.value = it

                        getApplication<Application>()
                            .getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
                            .edit()
                            .putString("JWT_TOKEN", it.token)
                            .apply()
                    }
                } else {
                    errorMessage.value = "Credenciales incorrectas"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error de red: ${e.message}"
            }
        }
    }
}