package com.example.appproductos

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val imageView = findViewById<ImageView>(R.id.imageViewProductDetail)
        val textViewName = findViewById<TextView>(R.id.textViewProductNameDetail)
        val textViewCantidad = findViewById<TextView>(R.id.textViewProductCantidad)
        val textViewEspecificaciones = findViewById<TextView>(R.id.textViewProductEspecificaciones)

        val nombre = intent.getStringExtra("nombre")
        val cantidad = intent.getIntExtra("cantidad", 0)
        val especificaciones = intent.getStringExtra("especificaciones")
        val imagenId = intent.getIntExtra("imagenId", R.drawable.ic_placeholder)

        textViewName.text = nombre
        textViewCantidad.text = "Cantidad disponible: $cantidad"
        textViewEspecificaciones.text = "Especificaciones: $especificaciones"

        Glide.with(this)
            .load(imagenId)
            .placeholder(R.drawable.ic_placeholder)
            .into(imageView)
    }
}
