package com.empresaficticia.SaludTotal.paciente.model

data class PacienteDto(
    val id: String,
    val nombre: String,
    val apellido: String,
    val cuil: String,
    val fechaNacimiento: String?
)
