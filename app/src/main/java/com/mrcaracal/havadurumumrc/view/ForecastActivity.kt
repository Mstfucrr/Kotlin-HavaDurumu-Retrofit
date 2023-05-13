package com.mrcaracal.havadurumumrc.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.mrcaracal.havadurumumrc.R
import com.mrcaracal.havadurumumrc.model.Day
import com.mrcaracal.havadurumumrc.model.Forecast
import com.mrcaracal.havadurumumrc.model.Forecastday
import com.mrcaracal.havadurumumrc.model.WeatherApiModel
import com.mrcaracal.havadurumumrc.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.favorite_city_item.*
import kotlinx.android.synthetic.main.forecast_day_activity.*
import kotlinx.android.synthetic.main.forecast_day_item.*

class ForecastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_day_activity)
        tabLayout.removeAllTabs()
        tabLayout.addTab(tabLayout.newTab().setText("Bugün"))
        tabLayout.addTab(tabLayout.newTab().setText("Yarın"))
        tabLayout.addTab(tabLayout.newTab().setText("2 Gün Sonra"))
        val cityName = intent.getStringExtra("cityName")
        val viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewmodel.refreshData(cityName!!)
        var data: WeatherApiModel? = null
        viewmodel.weather_data.observe(this, Observer { it ->
            it?.let {
                data = it
                GetDate(0, it)
            }})
        var position: Int? = null
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            @SuppressLint("SetTextI18n")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab?.position!!
                GetDate(position!!, data)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.e("TAG", "onTabUnselected: ${tab?.position}")

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.e("TAG", "onTabReselected: ${tab?.position}")
            }

        })

        btnForecast_Hourly.setOnClickListener {
            val intent = Intent(this, HourlyActivity::class.java)
            intent.putExtra("cityName", cityName)
            intent.putExtra("position", position)
            startActivity(intent)
        }



    }
    @SuppressLint("SetTextI18n")
    fun GetDate(position: Int, data: WeatherApiModel?) {
        val forecastday: Forecastday? = data?.forecast?.forecastday?.get(position)
        Glide.with(this)
            .load("https:" + data?.forecast?.forecastday?.get(position)?.day?.condition?.icon)
            .into(forecast_img_icon)
        forecast_city_info.text = data?.location?.name + " / " + data?.location?.country
        forecast_uv.text = forecastday?.day?.uv.toString()
        forecast_max_wind.text = forecastday?.day?.maxwindKph.toString()
        forecast_avg_temp.text = forecastday?.day?.avgtempC.toString()
        forecast_max_temp.text = forecastday?.day?.maxtempC.toString()
        forecast_min_temp.text = forecastday?.day?.mintempC.toString()
        forecast_avg_hum.text = forecastday?.day?.avghumidity.toString()
        forecast_sunrise.text = forecastday?.astro?.sunrise
        forecast_sunset.text =  forecastday?.astro?.sunset
        forecast_rain.text =  forecastday?.day?.totalprecipMm.toString()
        forecast_cond_text.text = forecastday?.day?.condition?.text
        forecast_date.text = forecastday?.date
    }


}