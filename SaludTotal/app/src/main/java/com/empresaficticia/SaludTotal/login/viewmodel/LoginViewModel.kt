package com.empresaficticia.SaludTotal.login.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.empresaficticia.SaludTotal.login.model.LoginRequest
import com.empresaficticia.SaludTotal.login.model.LoginResponse
import com.empresaficticia.SaludTotal.retrofitClient.RestClient
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val loginResult = MutableLiveData<LoginResponse?>()
    val error = MutableLiveData<String?>()

    fun hacerLogin(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = RestClient.instance.login(LoginRequest(email, password))
                guardarToken(response.Token)
                loginResult.postValue(response)
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error: ${e.message}", e)
                error.postValue("Credenciales inválidas o error de conexión")
            }
        }
    }

    private fun guardarToken(token: String) {
        val prefs = getApplication<Application>().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("jwt_token", token).apply()
    }
}
