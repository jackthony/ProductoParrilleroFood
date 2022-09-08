package com.example.productoparrillerofood.view.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productoparrillerofood.MainActivity
import com.example.productoparrillerofood.R
import com.example.productoparrillerofood.databinding.ActivityPlatoBinding
import com.example.productoparrillerofood.model.Plato
import com.example.productoparrillerofood.view.adapter.PlatoAdapter

class PlatoActivity : AppCompatActivity() {
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

                /** Completar con ubicacion
                R.id.aboutActivity -> {
                    val intent3 = Intent(this, AboutActivity::class.java)
                    startActivity(intent3)
                    overridePendingTransition(0,0)
                }**/
            }
            true
        }
        // end bottom navigation

        // listView

        val listPlatos = listOf(
            Plato("Champiñones a la Parrilla","30 a 40 minutos", "s/42.50", "Es un plato muy delicioso para personas que son vegetarianas.", R.drawable.p_champinonesparrilla),
            Plato("Anticucho de corazon de res", "20 a 30 minutos","s/39.00","Es un plato tipico muy rico, que es muy altamente demandado entre nuestros comensales",R.drawable.p_anticuchos)
        )

        val adapter = PlatoAdapter(this, listPlatos)

        binding.listviewPlatos.adapter = adapter

        binding.listviewPlatos.setOnItemClickListener{parent, view, position, id ->
            val intent = Intent(this, PlatoDetalleActivity::class.java)
            intent.putExtra("plato", listPlatos[position])
            startActivity(intent)
        }

        // end listView
    }
}