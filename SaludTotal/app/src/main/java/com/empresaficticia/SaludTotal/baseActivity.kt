package com.empresaficticia.SaludTotal.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.empresaficticia.SaludTotal.databinding.ActivityBaseBinding
import com.empresaficticia.SaludTotal.home.view.HomeActivity
import com.empresaficticia.SaludTotal.login.view.LoginActivity
import com.empresaficticia.SaludTotal.register.view.RegisterActivity

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargar contenido dinámico del hijo
        val childLayout = layoutInflater.inflate(getLayoutResourceId(), binding.frameContenido, false)
        binding.frameContenido.addView(childLayout)

        // Menú superior
        binding.btnInicio.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.btnRegistro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    abstract fun getLayoutResourceId(): Int
}
