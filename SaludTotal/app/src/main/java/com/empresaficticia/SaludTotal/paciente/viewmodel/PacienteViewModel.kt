package com.empresaficticia.SaludTotal.paciente.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.empresaficticia.SaludTotal.paciente.model.PacienteDto
import com.empresaficticia.SaludTotal.paciente.model.PacienteRequest
import com.empresaficticia.SaludTotal.paciente.repository.PacienteRepository
import kotlinx.coroutines.launch

class PacienteViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = PacienteRepository()
    val pacientes = MutableLiveData<List<PacienteDto>>()
    val mensaje = MutableLiveData<String?>()
    val nuevoToken = MutableLiveData<String?>()

    fun cargarPacientes() {
        viewModelScope.launch {
            try {
                val res = repo.getPacientes()
                if (res.isSuccessful) {
                    pacientes.value = res.body()
                } else {
                    mensaje.value = "Error al cargar pacientes"
                }
            } catch (e: Exception) {
                mensaje.value = "Error de red: ${e.message}"
            }
        }
    }

    fun seleccionarPaciente(id: String) {
        viewModelScope.launch {
            try {
                val res = repo.seleccionarPaciente(id)
                if (res.isSuccessful) {
                    nuevoToken.value = res.body()?.token
                    mensaje.value = res.body()?.mensaje
                } else {
                    mensaje.value = "Error al seleccionar paciente"
                }
            } catch (e: Exception) {
                mensaje.value = "Error de red: ${e.message}"
            }
        }
    }

    fun verificarCrearPaciente() {
        viewModelScope.launch {
            try {
                val res = repo.verificarCrearPaciente()
                mensaje.value = res.body()?.get("mensaje")
            } catch (e: Exception) {
                mensaje.value = "Error al verificar creación: ${e.message}"
            }
        }
    }

    fun crearPaciente(paciente: PacienteRequest) {
        viewModelScope.launch {
            try {
                val res = repo.crearPaciente(paciente)
                if (res.isSuccessful) {
                    nuevoToken.value = res.body()?.token
                    mensaje.value = res.body()?.mensaje
                } else {
                    mensaje.value = "Error al crear paciente"
                }
            } catch (e: Exception) {
                mensaje.value = "Error de red: ${e.message}"
            }
        }
    }
}
