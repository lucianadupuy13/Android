package com.empresaficticia.SaludTotal.register.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.empresaficticia.SaludTotal.databinding.ActivityRegisterBinding
import com.empresaficticia.SaludTotal.home.view.HomeActivity
import com.empresaficticia.SaludTotal.register.model.RegisterRequest
import com.empresaficticia.SaludTotal.register.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val request = RegisterRequest(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString(),
                confirmPassword = binding.etConfirmPassword.text.toString(),
                name = binding.etName.text.toString(),
                secondName = binding.etSecondName.text.toString()
            )

            viewModel.register(request)
        }

        viewModel.registerResult.observe(this) {
            if (it?.success == true) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it ?: "Error", Toast.LENGTH_SHORT).show()
        }
    }
}
