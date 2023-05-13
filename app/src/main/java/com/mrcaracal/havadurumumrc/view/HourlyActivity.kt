package com.mrcaracal.havadurumumrc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mrcaracal.havadurumumrc.R
import com.mrcaracal.havadurumumrc.model.WeatherApiModel
import com.mrcaracal.havadurumumrc.viewmodel.MainViewModel

class HourlyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly)
        val cityName = intent.getStringExtra("cityName")
        val position = intent.getIntExtra("position", 0)
        val viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewmodel.refreshData(cityName!!)
        var data: WeatherApiModel? = null

        viewmodel.weather_data.observe(this, Observer { it ->
            it?.let {
                data = it
                Log.i("TAG", "onCreate:" + data?.forecast?.forecastday?.get(position!!)?.hour?.get(0)?.tempC)

            }})
    }
}