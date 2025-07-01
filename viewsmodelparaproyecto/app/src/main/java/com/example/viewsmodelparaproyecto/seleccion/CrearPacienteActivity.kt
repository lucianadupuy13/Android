package com.example.viewsmodelparaproyecto.seleccion

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.viewsmodelparaproyecto.databinding.ActivityCrearPacienteBinding

class CrearPacienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearPacienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            if (validar()) {
                // TODO: Llamar a tu API para crear paciente
                Toast.makeText(this, "Paciente guardado (demo)", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        binding.btnCancelar.setOnClickListener { finish() }
    }

    private fun validar(): Boolean = with(binding) {
        val nombre  = etNombre.text.toString().trim()
        val apellido = etApellido.text.toString().trim()
        val dni     = etDni.text.toString().trim()
        val pais    = etCodPais.text.toString().trim()
        val area    = etCodArea.text.toString().trim()
        val linea   = etNumLinea.text.toString().trim()
        val dir     = etDireccion.text.toString().trim()

        val telefono = "$pais$area$linea"

        val dniValido  = Regex("^\\d{7,8}$").matches(dni)
        val telValido  = Regex("^\\+?\\d{10,13}$").matches(telefono)
        val dirValida  = dir.length >= 10

        if (!dniValido) etDni.error = "DNI inválido";
        if (!telValido) etNumLinea.error = "Teléfono inválido"
        if (!dirValida) etDireccion.error = "Mínimo 10 caracteres"

        return dniValido && telValido && dirValida && nombre.isNotBlank() && apellido.isNotBlank()
    }
}