package com.example.viewsmodelparaproyecto.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewsmodelparaproyecto.databinding.ActivitySobreNosotrosBinding

class SobreNosotrosActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySobreNosotrosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySobreNosotrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Link interno a «Profesionales»
        binding.tvGoProfesionales.setOnClickListener {
            startActivity(
                Intent(this, ProfesionalesActivity::class.java)
            )
        }

        binding.btnBack?.setOnClickListener { finish() }
    }
}