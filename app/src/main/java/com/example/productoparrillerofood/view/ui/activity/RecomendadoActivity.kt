package com.example.productoparrillerofood.view.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
    private val viewModel by lazy { ViewModelProvider(this)[RecomendadoViewModel::class.java] }
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


                R.id.ubicacionActivity -> {
                    val intent3 = Intent(this, UbicacionActivity::class.java)
                    startActivity(intent3)
                    overridePendingTransition(0,0)
                }
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
        recomendadoList.add(Recomendado("Chorizos a la parrilla", "Los chorizos a la parrilla de nuestro restaurante son uno de los mejores a nivel nacional debeido a que son expuesto a la parrilla al tiempo correcto", "https://www.que.es/wp-content/uploads/2021/02/Los-beneficios-que-no-conoces-del-chorizo.jpg"))
        recomendadoList.add(Recomendado("Molleja a la parrilla", "Este plato es preparado con mucho empeño en nuestro restaurante, ya que es un plato que requiere mucha atencion del chef y aqui en el parrillero realizamos este plato con mucha dedicacion.", "https://sp-ao.shortpixel.ai/client/to_auto,q_glossy,ret_img,w_928,h_696/https://parrillas.top/wp-content/uploads/2019/08/5565877441_7181c8b4a1_b-1024x768.jpg"))
        recomendadoList.add(Recomendado("Pollo a la parrilla", "Este plato en el restaurant lo complementamos con papa, camote , ensalada y sus ricas cremas, quedando asi un plato delicioso.", "https://decomidaperuana.com/wp-content/uploads/2020/11/pollo-a-la-parrilla.jpg"))
        recomendadoList.add(Recomendado("Lomo a la parrilla", "Este plato en el restaurant lo complementamos con papa, camote , ensalada y sus ricas cremas, quedando asi un plato delicioso.", "https://decomidaperuana.com/wp-content/uploads/2020/11/pollo-a-la-parrilla.jpg"))
        recomendadoList.add(Recomendado("Especial parrillero", "Este plato es uno de los mas completos que se tiene en el restaurant debido a la variedad de carnes que contiene, es uno de los platos mas ricos que hay.", "https://img.restaurantguru.com/w550/h367/r877-meat-El-Parrillero-2021-09-1.jpgl"))
        recomendadoList.add(Recomendado("Parrillero mixto", "Este plato es el mas completo que hay, ya que contiene todos los tipos de carnes que hay, ademas de complementos como el chorizo y la molleja, es uno de los platos mas pedidos en familia debido a su gran variedad y excelente sabor.", "https://www.laferiataypa.com/huacho/wp-content/uploads/2020/07/WhatsApp-Image-2020-07-18-at-5.46.09-PM.jpeg"))


        adapter.setListData(recomendadoList)
        adapter.notifyDataSetChanged()
        shimmer_view_container.visibility = View.GONE

        // end recyclerview

        //aplicando FIREBASE
        val myRef = database.getReference("Recomendado")
        myRef.child(myRef.push().key.toString()).setValue(recomendadoList)



    }

    //observeData(binding)
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