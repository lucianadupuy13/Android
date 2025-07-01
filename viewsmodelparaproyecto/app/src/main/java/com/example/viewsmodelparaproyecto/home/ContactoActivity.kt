package com.example.viewsmodelparaproyecto.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewsmodelparaproyecto.databinding.ActivityContactoBinding

class ContactoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón «Abrir en Google Maps»
        binding.btnOpenMaps.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=España+831,+Goya,+Corrientes")
            startActivity(Intent(Intent.ACTION_VIEW, gmmIntentUri))
        }

        // Clic en correo
        binding.tvEmail.setOnClickListener {
            val mail = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:contacto@centrosalud.com"))
            startActivity(Intent.createChooser(mail, "Enviar correo"))
        }

        // (Opcional) botón «Volver» si lo incluyes en el layout
        binding.btnBack?.setOnClickListener { finish() }
    }
}