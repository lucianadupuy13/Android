 package com.example.viewsmodelparaproyecto.auth   // ← mismo paquete que creaste

    import android.os.Bundle
    import androidx.appcompat.app.AppCompatActivity
    import com.example.viewsmodelparaproyecto.databinding.ActivityAccessDeniedBinding

    class AccessDeniedActivity : AppCompatActivity() {

        private lateinit var binding: AccessDeniedBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            // ViewBinding
            binding = ActivityAccessDeniedBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // (Opcional) Cambiar textos dinámicamente
            // binding.tvTitle.text = "Acceso Restringido"
        }
    }