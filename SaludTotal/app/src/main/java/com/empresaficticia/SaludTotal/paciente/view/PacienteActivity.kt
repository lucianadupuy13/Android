package com.empresaficticia.SaludTotal.paciente.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.empresaficticia.SaludTotal.databinding.ActivityPacienteBinding
import com.empresaficticia.SaludTotal.paciente.viewmodel.PacienteViewModel
import com.empresaficticia.SaludTotal.paciente.model.PacienteRequest
import com.empresaficticia.SaludTotal.paciente.model.ContactoRequest
import com.empresaficticia.SaludTotal.home.view.HomeActivity

class PacienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPacienteBinding
    private val viewModel: PacienteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCrearPaciente.setOnClickListener {
            val paciente = PacienteRequest(
                nombre = binding.etNombre.text.toString(),
                apellido = binding.etApellido.text.toString(),
                cuil = binding.etCuil.text.toString(),
                fechaNacimiento = binding.etFechaNac.text.toString(),
                idContactoNavigation = ContactoRequest(
                    telefono = binding.etTelefono.text.toString(),
                    direccion = binding.etDireccion.text.toString()
                )
            )
            viewModel.crearPaciente(paciente)
        }

        viewModel.mensaje.observe(this, Observer {
            Toast.makeText(this, it ?: "Sin mensaje", Toast.LENGTH_SHORT).show()
        })

        viewModel.nuevoToken.observe(this, Observer { token ->
            token?.let {
                getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
                    .edit().putString("JWT_TOKEN", it).apply()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        })
    }
}
