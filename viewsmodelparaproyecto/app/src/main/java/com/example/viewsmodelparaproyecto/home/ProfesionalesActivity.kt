package com.example.viewsmodelparaproyecto.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewsmodelparaproyecto.databinding.ActivityProfesionalesBinding

class ProfesionalesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfesionalesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfesionalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack?.setOnClickListener { finish() }
    }
}