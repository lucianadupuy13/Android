package com.example.viewsmodelparaproyecto.paciente

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewsmodelparaproyecto.databinding.ActivityTurnosReservadosBinding

class TurnosReservadosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTurnosReservadosBinding
    private val adapter = ReservadosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTurnosReservadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerReservados.layoutManager = LinearLayoutManager(this)
        binding.recyclerReservados.adapter = adapter

        // TODO: paginar desde API
        adapter.submitList(demoReservados())
    }

    private fun demoReservados(): List<TurnoReservado> = listOf(
        TurnoReservado(1, "2025-07-10 09:00", "En espera", "Consulta", "Dr. Pérez", "Juan Gómez", esPropio = true),
        TurnoReservado(2, "2025-07-12 11:00", "Confirmado", "Privado", "Dr. Ortega", "Reservado", esPropio = false)
    )
}

data class TurnoReservado(
    val numero: Int,
    val fechaHora: String,
    val estado: String,
    val categoria: String,
    val profesional: String,
    val paciente: String,
    val esPropio: Boolean
)