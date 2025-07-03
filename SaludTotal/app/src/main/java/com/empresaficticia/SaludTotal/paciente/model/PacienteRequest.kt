package com.empresaficticia.SaludTotal.paciente.model

data class ContactoRequest(
    val telefono: String?,
    val direccion: String?
)

data class PacienteRequest(
    val nombre: String,
    val apellido: String,
    val cuil: String,
    val fechaNacimiento: String,
    val idContactoNavigation: ContactoRequest
)
