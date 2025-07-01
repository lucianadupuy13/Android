package com.example.viewsmodelparaproyecto.paciente

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.viewsmodelparaproyecto.databinding.ActivityUpdateTurnoBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class UpdateTurnoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTurnoBinding
    private val categorias = listOf("Consulta", "Laboratorio", "Vacunación")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTurnoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* ---------- Poblar datos recibidos (demo) ---------- */
        val turnoId = intent.getIntExtra("TURNO_ID", -1)
        // TODO: traer turno por ID desde API y rellenar vistas
        binding.spCategoria.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)

        binding.etFecha.setOnClickListener { mostrarDatePicker() }

        binding.btnGuardar.setOnClickListener {
            Toast.makeText(this, "Cambios guardados (demo)", Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.btnCancelar.setOnClickListener { finish() }
    }

    private fun mostrarDatePicker() {
        val now = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, y, m, d ->
                val fecha = LocalDate.of(y, m + 1, d)
                binding.etFecha.setText(fecha.format(DateTimeFormatter.ISO_DATE))
            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        ).apply { datePicker.minDate = now.timeInMillis }
            .show()
    }
}