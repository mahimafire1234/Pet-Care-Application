package com.mahima.animestreamingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mahima.animestreamingapp.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var arrayList : ArrayList<maps> = ArrayList<maps>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
//        add data in the data class
        arrayList.add(maps(
            27.717568,85.348403,"Pet Care MainOffice"
        ))
        arrayList.add(maps(
            27.7061949,85.3300394,"Pet Care Office"
        ))

        for (location in arrayList){
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(location.latitide,location.longitude))
                    .title(location.title)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            )
        }
//        for animation
        mMap.animateCamera(
            CameraUpdateFactory
                .newLatLngZoom(LatLng(27.717568,85.348403),16F)
            ,4000
            ,null
        )

        mMap.uiSettings.isZoomControlsEnabled=true

    }
}