package com.example.productoparrillerofood.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productoparrillerofood.model.Recomendado
import com.example.productoparrillerofood.network.Repositorio

class RecomendadoViewModel : ViewModel(){

        private val repositorio = Repositorio()

        fun fetchRecomendadoData() : LiveData<MutableList<Recomendado>> {
            val mutableData = MutableLiveData<MutableList<Recomendado>>()
            repositorio.getRecomendadosData().observeForever{
                recomendadoList -> mutableData.value = recomendadoList
            }
            return mutableData
        }
}