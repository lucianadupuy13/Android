package com.example.viewsmodelparaproyecto.seleccion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.viewsmodelparaproyecto.databinding.ActivitySeleccionPacienteBinding

class SeleccionPacienteActivity : AppCompatActivity(), PacienteAdapter.OnPacienteClick {

    private lateinit var binding: ActivitySeleccionPacienteBinding
    private val adapter = PacienteAdapter(this)

    private val maxPacientes = 6      // Ajusta según tu ViewModel
    private val listaPacientesDemo = mutableListOf(
        Paciente(1, "Juan", "Gómez"),
        Paciente(2, "Ana", "Pérez")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerPacientes.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerPacientes.adapter = adapter

        renderLista()
    }

    private fun renderLista() {
        val espaciosDisponibles = maxPacientes - listaPacientesDemo.size
        val items = listaPacientesDemo.map { PacienteItem.Existente(it) } +
                List(espaciosDisponibles) { PacienteItem.Nuevo }
        adapter.submitList(items)
    }

    /* ---------- Callback ---------- */
    override fun onPacienteClick(paciente: Paciente) {
        AlertDialog.Builder(this)
            .setTitle("Seleccionar")
            .setMessage("¿Usar a ${paciente.nombre} ${paciente.apellido}?")
            .setPositiveButton("Sí") { _, _ ->
                // TODO: enviar ID seleccionado al backend o guardar en ViewModel
                finish()
            }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onAgregarNuevo() {
        startActivity(Intent(this, CrearPacienteActivity::class.java))
    }
}