package com.example.productoparrillerofood.view.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productoparrillerofood.MainActivity
import com.example.productoparrillerofood.R

import com.example.productoparrillerofood.databinding.ActivityPlatoBinding
import com.example.productoparrillerofood.model.Plato
import com.example.productoparrillerofood.view.adapter.PlatoAdapter
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PlatoActivity : AppCompatActivity() {
    private val database = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPlatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // bottom navigation
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.platoActivity)

        bottomNavigationView.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.mainActivity -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0,0)
                }
                R.id.platoActivity -> {
                }
                R.id.recomendadoActivity -> {
                    val intent2 = Intent(this, RecomendadoActivity::class.java)
                    startActivity(intent2)
                    overridePendingTransition(0,0)
                }


                R.id.ubicacionActivity -> {
                    val intent3 = Intent(this, UbicacionActivity::class.java)
                    startActivity(intent3)
                    overridePendingTransition(0,0)
                }
            }
            true
        }
        // end bottom navigation

        // listView

        val listPlatos = listOf(
            Plato("Champiñones a la Parrilla","30 a 40 minutos", "s/42.50", "Es un plato muy delicioso para personas que son vegetarianas.", R.drawable.p_champinonesparrilla),
            Plato("Anticucho de corazon de res", "20 a 30 minutos","s/39.00","Es un plato tipico muy rico, que es muy altamente demandado entre nuestros comensales",R.drawable.p_anticuchos),
            Plato("Chorizos a la parrilla", "15 a 20 minutos","s/36.0","Es un plato muy conocido, y rapido de concinar",R.drawable.platillo3),
            Plato("Molleja de res", " 20 a 30 minutos","s/45.0","Es un plato delicioso y muy conocido, excelente para darse un engreimiento",  R.drawable.platillo4),
            Plato("Pollo a la Parrilla", " 25 a 30 minutos","s/40.0","Es el plato màs vendido que tenemos y es una buena eleccion para comer debido a su rico sabor",  R.drawable.platillo5),
            Plato("Lomo a la Parrilla", " 30 a 40 minutos","s/50.0","Uno de los platos mas deliciosos que tenemos y ademas uno de los mas queridos por los clientes",  R.drawable.platillo6),
            Plato("Especial parrillero", " 40 a 50 minutos","s/80.0","Un plato especial que contiene carne, pollo y chancho a la parrilla", R.drawable.platillo7),
            Plato("Parrillero mixto", " 40 a 50 minutos","s/100.0","Este plato contiene diversos tipos de carnes y ademàs chorizos entre otros manjares", R.drawable.platillo8),
            Plato("Ronda Parrillera", "  40 minutos","s/120.0","Plato conformado por todos los tipos de carnes y complementos, este plato es recomendable para 4 personas",R.drawable.platillo9),
            Plato("Chancho a la parrilla peruana", "  45 a 50 minutos","s/80.0","Plato conformado por carne de cerdo, que es cocinada en su punto logrando un saber delicioso",R.drawable.platillo10),
            Plato("Pollo y carne del parrillero", "  45 a 50 minutos","s/80.0","Plato conformado por pollo y carne de res, que son preparadas de una forma especial que solo se realiza en nuestro restaurante",R.drawable.platillo11),
            Plato("Parrilla Parrillero", "  45 a 50 minutos","s/70.0","Plato unico en nuestro restaurante, que esta conformado por una variedad de carnes y una bebida de comple,emtp",R.drawable.platillo12),
            Plato("Parrilla de vendado", "  50 a 60 minutos","s/80.0","Plato hecho con carne de venado traído desde la selva, es uno de los platos más ricos y especiales de nuestro menu",R.drawable.platillo13),
            Plato("Parrilla de cuy", "  30 a 40 minutos","s/90.0","Plato hecho con carne de cuy que es muy tradicional en el Peru por su agradable sabor",R.drawable.platillo14)
        )

        val adapter = PlatoAdapter(this, listPlatos)

        binding.listviewPlatos.adapter = adapter

        binding.listviewPlatos.setOnItemClickListener{parent, view, position, id ->
            val intent = Intent(this, PlatoDetalleActivity::class.java)
            intent.putExtra("plato", listPlatos[position])
            startActivity(intent)
        }

        //aplicando FIREBASE
        val myRef = database.getReference("Plato")
        myRef.child(myRef.push().key.toString()).setValue(listPlatos)

        // end listView
    }
}