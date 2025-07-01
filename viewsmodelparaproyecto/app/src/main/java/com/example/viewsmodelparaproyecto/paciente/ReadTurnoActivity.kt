package com.example.viewsmodelparaproyecto.paciente

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewsmodelparaproyecto.databinding.ActivityReadTurnosBinding

class ReadTurnosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadTurnosBinding
    private val adapter = TurnoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadTurnosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerTurnos.layoutManager = LinearLayoutManager(this)
        binding.recyclerTurnos.adapter = adapter

        binding.fabNuevoTurno.setOnClickListener {
            startActivity(Intent(this, CreateTurnoActivity::class.java))
        }

        // TODO: cargar turnos desde API
        adapter.submitList(demoTurnos())
    }

    /* --- Muestra de datos --- */
    private fun demoTurnos(): List<Turno> = listOf(
        Turno("Clínica", "Dra. Gómez", "2025-07-20", "10:30", "En espera", "Consulta", id = 1),
        Turno("Pediatría", "Dr. Ortega", "2025-07-01", "09:00", "Confirmado", "Vacunación", id = 2)
    )
}