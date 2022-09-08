package com.example.productoparrillerofood.view.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productoparrillerofood.MainActivity
import com.example.productoparrillerofood.R
import com.example.productoparrillerofood.databinding.ActivityRecomendadoBinding
import com.example.productoparrillerofood.model.Recomendado
import com.example.productoparrillerofood.view.adapter.RecomendadoAdapter
import com.example.productoparrillerofood.viewmodel.RecomendadoViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_recomendado.*


class RecomendadoActivity : AppCompatActivity() {

    private lateinit var adapter: RecomendadoAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(RecomendadoViewModel::class.java) }
    private val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecomendadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // bottom navigation
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.recomendadoActivity)

        bottomNavigationView.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.mainActivity -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0,0)
                }
                R.id.platoActivity -> {
                    val intent2 = Intent(this, PlatoActivity::class.java)
                    startActivity(intent2)
                    overridePendingTransition(0,0)
                }
                R.id.recomendadoActivity -> {
                }

                /** Agregar el activity de ubicacion
                R.id.aboutActivity -> {
                    val intent3 = Intent(this, AboutActivity::class.java)
                    startActivity(intent3)
                    overridePendingTransition(0,0)
                } **/
            }
            true
        }
        // end bottom navigation

        // recyclerview
        adapter = RecomendadoAdapter(this)
        binding.recyclerviewFood.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewFood.adapter = adapter

        val recomendadoList : MutableList<Recomendado> = mutableListOf<Recomendado>()

        recomendadoList.add(Recomendado("Champiñones a la Parrilla", "Es un plato muy delicioso para personas que son vegetarianas.", "https://imgmedia.buenazo.pe/650x358/buenazo/original/2021/07/02/60dfbf473b0ec002c502f063.jpg"))
        recomendadoList.add(Recomendado("Anticucho de corazon de res", "En nuestro restaurant resaltan los champiñones a la parrilla. Debido a que se suele elaborar con los champiñones mas frescos que se pueden encontrar en el mercado, lo cual hace que nuestro plato sea exquisito.", "https://comidasperuanas.net/wp-content/uploads/2015/06/Anticuchos-de-Coraz%C3%B3n-Peruanos.jpg"))



        adapter.setListData(recomendadoList)
        adapter.notifyDataSetChanged()
        shimmer_view_container.visibility = View.GONE

        // end recyclerview

        //aplicando FIREBASE
        val myRef = database.getReference("Recomendado")
        myRef.child(myRef.push().key.toString()).setValue(recomendadoList.get(1))

        //observeData(binding)

    }

    fun observeData(binding : ActivityRecomendadoBinding) {
        binding.shimmerViewContainer.startShimmer()
        viewModel.fetchRecomendadoData().observe(this, Observer {
            shimmer_view_container.stopShimmer()
            shimmer_view_container.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}