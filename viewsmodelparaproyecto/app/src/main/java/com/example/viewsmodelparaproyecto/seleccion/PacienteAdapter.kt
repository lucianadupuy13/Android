package com.example.viewsmodelparaproyecto.seleccion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewsmodelparaproyecto.databinding.ItemPacienteBinding
import com.example.viewsmodelparaproyecto.databinding.ItemPacienteNuevoBinding

class PacienteAdapter(
    private val listener: OnPacienteClick
) : ListAdapter<PacienteItem, RecyclerView.ViewHolder>(DIFF) {

    interface OnPacienteClick {
        fun onPacienteClick(paciente: Paciente)
        fun onAgregarNuevo()
    }

    override fun getItemViewType(position: Int) =
        when (getItem(position)) {
            is PacienteItem.Existente -> 0
            PacienteItem.Nuevo       -> 1
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == 0) {
            PacienteVH(
                ItemPacienteBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else {
            NuevoVH(
                ItemPacienteNuevoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is PacienteItem.Existente -> (holder as PacienteVH).bind(item.paciente)
            PacienteItem.Nuevo        -> (holder as NuevoVH).bind()
        }
    }

    inner class PacienteVH(private val b: ItemPacienteBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(p: Paciente) {
            b.tvNombre.text = "${p.nombre} ${p.apellido}"
            b.btnSeleccionar.setOnClickListener { listener.onPacienteClick(p) }
        }
    }

    inner class NuevoVH(b: ItemPacienteNuevoBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind() {
            itemView.setOnClickListener { listener.onAgregarNuevo() }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<PacienteItem>() {
            override fun areItemsTheSame(o: PacienteItem, n: PacienteItem) =
                (o is PacienteItem.Existente && n is PacienteItem.Existente && o.paciente.id == n.paciente.id)
                        || (o is PacienteItem.Nuevo && n is PacienteItem.Nuevo)

            override fun areContentsTheSame(o: PacienteItem, n: PacienteItem) = o == n
        }
    }
}