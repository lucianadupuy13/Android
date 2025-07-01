data class Paciente(val id: Int, val nombre: String, val apellido: String)

/* Item sealed para Recycler */
sealed class PacienteItem {
    data class Existente(val paciente: Paciente) : PacienteItem()
    object Nuevo : PacienteItem()
}