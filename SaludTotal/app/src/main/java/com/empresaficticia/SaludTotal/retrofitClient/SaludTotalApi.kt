package com.empresaficticia.SaludTotal.retrofitClient

import com.empresaficticia.SaludTotal.paciente.model.*
import retrofit2.Response
import retrofit2.http.*

interface SaludTotalApi {

    @GET("api/pacienteapi/pacientes")
    suspend fun getPacientes(): Response<List<PacienteDto>>

    @POST("api/pacienteapi/seleccionar")
    suspend fun seleccionarPaciente(@Body pacienteId: String): Response<PacienteResponse>

    @GET("api/pacienteapi/crear")
    suspend fun verificarCrearPaciente(): Response<Map<String, String>>

    @POST("api/pacienteapi/crear")
    suspend fun crearPaciente(@Body paciente: PacienteRequest): Response<PacienteResponse>
}
