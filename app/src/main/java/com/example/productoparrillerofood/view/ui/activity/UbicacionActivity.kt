package com.example.productoparrillerofood.view.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productoparrillerofood.MainActivity
import com.example.productoparrillerofood.R
import com.example.productoparrillerofood.databinding.ActivityUbicacionBinding
import com.example.productoparrillerofood.model.Ubicacion

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class UbicacionActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUbicacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // bottom navigation
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setSelectedItemId(R.id.ubicacionActivity)

        bottomNavigationView.setOnItemSelectedListener{
                item ->
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
                    val intent3 = Intent(this, RecomendadoActivity::class.java)
                    startActivity(intent3)
                    overridePendingTransition(0,0)

                }


                R.id.ubicacionActivity -> {

                }
            }
            true
        }
        // end bottom navigation


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.maps) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val ubicacion = Ubicacion()
        val zoom= 18f
        // Add a marker in Sydney and move the camera
        val centerMap = LatLng(ubicacion.latitude, ubicacion.longitude)
        mMap.addMarker(MarkerOptions().position(centerMap).title("Parrillero Fast Food"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerMap,zoom))
    }

}