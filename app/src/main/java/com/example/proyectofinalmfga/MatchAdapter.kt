package com.example.proyectofinalmfga

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalmfga.databinding.ItemMatchesBinding


class MatchAdapter(val matches: List<Match>) : RecyclerView.Adapter<MatchHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): MatchHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMatchesBinding.inflate(inflater, parent, false)
        return MatchHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MatchHolder,
        position: Int
    ) {
        val matchActual = matches[position]

        // Asignamos la información de la clase a los TextView del XML
        holder.binding.tvwRival.text = matchActual.equipoRival
        holder.binding.tvwFecha.text = matchActual.fecha
        holder.binding.tvwCancha.text = matchActual.cancha
    }

    // Estructura simplificada de una sola línea
    override fun getItemCount() = matches.size
}