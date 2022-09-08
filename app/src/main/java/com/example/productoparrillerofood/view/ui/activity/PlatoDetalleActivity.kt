package com.example.productoparrillerofood.view.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productoparrillerofood.R
import com.example.productoparrillerofood.model.Plato
import kotlinx.android.synthetic.main.activity_plato_detalle.*

class PlatoDetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plato_detalle)

        val plato = intent.getSerializableExtra("plato") as Plato
        tvPlatoDetailName.text = plato.nombre
        tvPlatoDetailPrecio.text = plato.precio
        tvPlatoDetailSchedule.text = plato.tiempoPreparacion
        tvPlatoDetailDescription.text = plato.descripcion
        ivPlatoDetailimage.setImageResource(plato.imagen)
    }
}