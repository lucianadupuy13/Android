package com.empresaficticia.SaludTotal.register.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.empresaficticia.SaludTotal.register.model.RegisterRequest
import com.empresaficticia.SaludTotal.AuthRepository
import com.empresaficticia.SaludTotal.register.model.RegisterResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    val registerResult = MutableLiveData<RegisterResponse?>()
    val errorMessage = MutableLiveData<String?>()

    fun register(request: RegisterRequest) {
        viewModelScope.launch {
            try {
                val response: Response<RegisterResponse> =
                    AuthRepository().register(request)

                if (response.isSuccessful) {
                    registerResult.value = response.body()
                } else {
                    errorMessage.value = "Error al registrar"
                }
            } catch (e: Exception) {
                errorMessage.value = "Error de red: ${e.message}"
            }
        }
    }
}
