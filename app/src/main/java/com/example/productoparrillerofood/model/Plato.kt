package com.example.productoparrillerofood.model
import java.io.Serializable

class Plato (
    val nombre:String,
    val tiempoPreparacion:String,
    val precio:String,
    val descripcion:String,
    val imagen:Int
) : Serializable