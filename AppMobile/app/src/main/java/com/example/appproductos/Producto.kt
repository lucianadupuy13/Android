package com.example.appproductos

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: String,
    val imagenResId: Int,
    val cantidadDisponible: Int,
    val especificaciones: String
)