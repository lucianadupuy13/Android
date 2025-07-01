package com.example.viewsmodelparaproyecto.auth   // ⬅️ mismo paquete donde creaste LoginActivity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.viewsmodelparaproyecto.databinding.ActivityRegisterBinding
import com.example.viewsmodelparaproyecto.MainActivity    // ⬅️ cambia si tu MainActivity está en otra ruta

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: RegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ---------- ViewBinding ----------
        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ---------- Botón Registrarse ----------
        binding.btnRegister.setOnClickListener {
            if (camposValidos()) {
                // TODO: Llamar a tu API / ViewModel para registrarse
                Toast.makeText(this, "Registro exitoso (demo)", Toast.LENGTH_SHORT).show()

                // Demo: volver a pantalla principal
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        // ---------- Link "¿Ya tienes cuenta? Inicia sesión" ----------
        binding.tvGoLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }

        // ---------- Botón "Volver al Inicio" ----------
        binding.btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    /** Valida campos; muestra errores inline y/o en tvError */
    private fun camposValidos(): Boolean {
        val nombre  = binding.etNombre.text.toString().trim()
        val apellido = binding.etApellido.text.toString().trim()
        val email   = binding.etEmail.text.toString().trim()
        val pass    = binding.etPassword.text.toString()
        val pass2   = binding.etConfirmPassword.text.toString()

        // Limpia mensaje de error global
        binding.tvError.visibility = android.view.View.GONE

        // Validaciones simples
        when {
            nombre.isBlank()   -> { binding.etNombre.error = "Ingresa tu nombre"; return false }
            apellido.isBlank() -> { binding.etApellido.error = "Ingresa tu apellido"; return false }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
            { binding.etEmail.error = "Correo inválido"; return false }
            pass.length < 6    -> { binding.etPassword.error = "Mínimo 6 caracteres"; return false }
            pass != pass2      -> { binding.etConfirmPassword.error = "No coincide"; return false }
        }
        return true
    }
}