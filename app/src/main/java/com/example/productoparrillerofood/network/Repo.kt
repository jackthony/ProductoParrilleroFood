package com.example.productoparrillerofood.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productoparrillerofood.model.Recomendado
import com.google.firebase.firestore.FirebaseFirestore

class Repo {

    fun getRecomendadosData(): LiveData<MutableList<Recomendado>> {
        val mutableData = MutableLiveData<MutableList<Recomendado>>()
        // obtener datos de firebase
        FirebaseFirestore
            .getInstance()
            .collection("recomendados")
            .get().addOnSuccessListener {
                    result -> val listData = mutableListOf<Recomendado>()
                                for (document in result){

                                    val imagen = document.getString("imagen")
                                    val nombre = document.getString("nombre")
                                    val resumen = document.getString("resumen")
                                    val recomendado = Recomendado(nombre!!, resumen!!, imagen!!)
                                    listData.add(recomendado)
                                    }
                    mutableData.value = listData
        }
        return mutableData
    }
}