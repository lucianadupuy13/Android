package com.example.appproductos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameEditText = findViewById<EditText>(R.id.editTextUsername)
        val secondnameEditText = findViewById<EditText>(R.id.editTextSecondName)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val passwordRepeatEditText = findViewById<EditText>(R.id.editTextPasswordRepeat)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val secondname = secondnameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val passwordRepeat = passwordRepeatEditText.text.toString()

            // Validar que las contraseñas coincidan
            if (password != passwordRepeat) {
                Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar contraseña segura
            val passwordPattern = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}\$")
            if (!password.matches(passwordPattern)) {
                Toast.makeText(
                    this,
                    "La contraseña debe tener al menos 8 caracteres, mayúscula, número y carácter especial.",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            // Armar el DTO como lo espera la API
            val registerDto = RegistroDto(
                email = email,
                password = password,
                confirmPassword = passwordRepeat,
                name = name,
                secondName = secondname
            )

            RetrofitClient.authService.registro(registerDto).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        finish() // volver a LoginActivity
                    } else {
                        Toast.makeText(this@RegisterActivity, "Registro fallido", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
