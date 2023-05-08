package com.mrcaracal.havadurumumrc.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.mrcaracal.havadurumumrc.R
class MapActivity : FragmentActivity() , OnMapReadyCallback {
    private lateinit var mapFragment: SupportMapFragment
    private var Lat = 0.0
    private var Lon = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map)
        val i = intent
        Lat = i.getDoubleExtra("lat", 0.0)
        Lon = i.getDoubleExtra("lon", 0.0)
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment
    }
    override fun onResume() {
        super.onResume()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val markerOptions = MarkerOptions()
            .position(LatLng(Lat, Lon))
            .title("Marker")
        googleMap.addMarker(markerOptions)

        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(Lat, Lon))
            .zoom(10f)
            .build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        // Marker'ı ekleyin
        googleMap.addMarker(
            MarkerOptions().position(LatLng(Lat, Lon))
                .title("Marker")
        )
    }
}