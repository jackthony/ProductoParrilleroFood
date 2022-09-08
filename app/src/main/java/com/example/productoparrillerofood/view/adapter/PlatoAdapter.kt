package com.example.productoparrillerofood.view.adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.productoparrillerofood.R
import com.example.productoparrillerofood.model.Plato
import kotlinx.android.synthetic.main.item_plato.view.*

class PlatoAdapter(
    private val miContexto :Context,
    private val listPlatos: List<Plato>) : ArrayAdapter<Plato>(
            miContexto, 0, listPlatos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(miContexto)
                        .inflate(R.layout.item_plato,
                            parent,
                    false)

        val plato = listPlatos[position]
        layout.textViewPlato.text = plato.nombre
        layout.textViewPlatoSchedule.text = plato.tiempoPreparacion
        layout.imageViewPlato.setImageResource(plato.imagen)

        return layout
    }
}