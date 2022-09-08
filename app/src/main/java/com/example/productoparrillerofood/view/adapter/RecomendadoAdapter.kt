package com.example.productoparrillerofood.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productoparrillerofood.R
import com.example.productoparrillerofood.model.Recomendado
import kotlinx.android.synthetic.main.item_recomendado.view.*


class RecomendadoAdapter
    (private val miContexto: Context):
    RecyclerView.Adapter<RecomendadoAdapter.RecomendadoViewHolder>() {

    private var dataList = mutableListOf<Recomendado>()

    fun setListData(data:MutableList<Recomendado>) {
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecomendadoViewHolder {
        val view = LayoutInflater.from(miContexto).inflate(R.layout.item_recomendado, parent, false)
        return RecomendadoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecomendadoViewHolder, position: Int) {
        val recomendado:Recomendado = dataList[position]
        holder.bindView(recomendado)
    }

    override fun getItemCount(): Int {
        if(dataList.size > 0) {
            return dataList.size
        } else {
            return 0
        }
    }

    inner class RecomendadoViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        fun bindView(recomendado:Recomendado){
            Glide.with(miContexto).load(recomendado.imagen).into(itemView.civRecomendadoImage)
            itemView.tvRecomendadoName.text = recomendado.nombre
            itemView.tvRecomendadoSummary.text = recomendado.resumen
        }
    }
}