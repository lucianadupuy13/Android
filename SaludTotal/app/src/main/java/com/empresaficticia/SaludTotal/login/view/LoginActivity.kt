package com.empresaficticia.SaludTotal.login.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.empresaficticia.SaludTotal.databinding.ActivityLoginBinding
import com.empresaficticia.SaludTotal.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Click en el botón de login
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                binding.tvError.visibility = View.GONE
                viewModel.hacerLogin(email, password)
            } else {
                binding.tvError.text = "Completa ambos campos"
                binding.tvError.visibility = View.VISIBLE
            }
        }

        // Ir al registro (opcional)
        binding.tvGoRegister.setOnClickListener {
            Toast.makeText(this, "Registro aún no implementado", Toast.LENGTH_SHORT).show()
        }

        // Volver al inicio (opcional)
        binding.btnHome.setOnClickListener {
            finish() // Vuelve atrás
        }

        // Observa el resultado del login
        viewModel.loginResult.observe(this) { response ->
            if (response != null) {
                Toast.makeText(this, "Bienvenido: ${response.email}", Toast.LENGTH_SHORT).show()
                // TODO: Navegar a HomeActivity si existe
                // startActivity(Intent(this, HomeActivity::class.java))
                // finish()
            }
        }

        // Observa errores
        viewModel.error.observe(this) { mensaje ->
            binding.tvError.text = mensaje
            binding.tvError.visibility = View.VISIBLE
        }
    }
}
