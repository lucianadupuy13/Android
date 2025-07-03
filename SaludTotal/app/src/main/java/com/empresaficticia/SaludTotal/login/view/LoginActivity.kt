package com.empresaficticia.SaludTotal.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.empresaficticia.SaludTotal.databinding.ActivityLoginBinding
import com.empresaficticia.SaludTotal.home.view.HomeActivity
import com.empresaficticia.SaludTotal.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(email, password)
        }

        viewModel.loginResult.observe(this) { result ->
            result?.let {
                Toast.makeText(this, "Login OK", Toast.LENGTH_SHORT).show()
                // Ir a Home o pantalla de selección de paciente
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it ?: "Error", Toast.LENGTH_SHORT).show()
        }
    }
}