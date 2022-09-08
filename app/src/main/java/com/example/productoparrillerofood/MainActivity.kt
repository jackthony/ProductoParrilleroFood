package com.example.productoparrillerofood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productoparrillerofood.databinding.ActivityMainBinding
import com.example.productoparrillerofood.view.ui.activity.PlatoActivity
import com.example.productoparrillerofood.view.ui.activity.RecomendadoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // bottom navigation
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.mainActivity)

        bottomNavigationView.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.mainActivity -> {
                }
                R.id.platoActivity -> {
                    val intent2 = Intent(this, PlatoActivity::class.java)
                    startActivity(intent2)
                    overridePendingTransition(0,0)
                }
                R.id.recomendadoActivity -> {
                    val intent2 = Intent(this, RecomendadoActivity::class.java)
                    startActivity(intent2)
                    overridePendingTransition(0,0)
                }

                /** AGREGAR UBICACION
                R.id.aboutActivity -> {
                    val intent3 = Intent(this, AboutActivity::class.java)
                    startActivity(intent3)
                    overridePendingTransition(0,0)
                } **/
            }
            true
        }
    }
}