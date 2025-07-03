package com.empresaficticia.SaludTotal.paciente.repository

import com.empresaficticia.SaludTotal.paciente.model.*
import com.empresaficticia.SaludTotal.retrofitClient.RetrofitClient
import retrofit2.Response

class PacienteRepository {
    private val api = RetrofitClient.saludTotalApi

    suspend fun getPacientes(): Response<List<PacienteDto>> = api.getPacientes()
    suspend fun seleccionarPaciente(id: String): Response<PacienteResponse> = api.seleccionarPaciente(id)
    suspend fun verificarCrearPaciente(): Response<Map<String, String>> = api.verificarCrearPaciente()
    suspend fun crearPaciente(request: PacienteRequest): Response<PacienteResponse> = api.crearPaciente(request)
}
