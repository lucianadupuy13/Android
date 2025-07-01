package com.example.viewsmodelparaproyecto.paciente

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.viewsmodelparaproyecto.databinding.ActivityCreateTurnoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class CreateTurnoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateTurnoBinding

    private val categorias = listOf(
        "Consulta", "Laboratorio", "Vacunación", "Cardiología", "Pediatría"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTurnoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* ---------- Categorías ---------- */
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCategoria.adapter = adapter

        /* ---------- Fecha ---------- */
        binding.etFecha.setOnClickListener { mostrarDatePicker() }

        /* ---------- Cargar horarios dinámicos ---------- */
        binding.spCategoria.setOnItemSelectedListener(SimpleListener { cargarHorarios() })
        binding.etFecha.addTextChangedListener(SimpleTextWatcher { cargarHorarios() })

        /* ---------- Solicitar turno ---------- */
        binding.btnSolicitar.setOnClickListener {
            if (validar()) {
                Toast.makeText(this, "Turno solicitado (demo)", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        binding.btnVolver.setOnClickListener { finish() }
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
        ).apply {
            datePicker.minDate = now.timeInMillis
        }.show()
    }

    private fun cargarHorarios() {
        val categoria = binding.spCategoria.selectedItem?.toString().orEmpty()
        val fecha = binding.etFecha.text.toString()
        if (categoria.isBlank() || fecha.isBlank()) return

        lifecycleScope.launch {
            val horas = withContext(Dispatchers.IO) {
                // ---------- Llamada a tu API ----------
                // Sustituye URL base
                val url =
                    "https://tu-servidor.com/api/obtenerHorarios?categoria=${categoria}&fecha=$fecha"
                runCatching { khttp.get(url).text }
                    .mapCatching { JSONArray(it) }
                    .getOrNull()
                    ?.let { 0.until(it.length()).map { idx -> it.getString(idx) } }
                    ?: emptyList()
            }

            val horaAdapter = ArrayAdapter(
                this@CreateTurnoActivity,
                android.R.layout.simple_spinner_item,
                if (horas.isEmpty()) listOf("Sin disponibilidad") else horas
            )
            horaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spHora.adapter = horaAdapter
        }
    }

    private fun validar(): Boolean {
        if (binding.spCategoria.selectedItem == null) {
            Toast.makeText(this, "Seleccione categoría", Toast.LENGTH_SHORT).show(); return false
        }
        if (binding.etFecha.text.isNullOrBlank()) {
            Toast.makeText(this, "Seleccione fecha", Toast.LENGTH_SHORT).show(); return false
        }
        if (binding.spHora.selectedItem == null || binding.spHora.selectedItem == "Sin disponibilidad") {
            Toast.makeText(this, "Seleccione hora válida", Toast.LENGTH_SHORT).show(); return false
        }
        return true
    }
}