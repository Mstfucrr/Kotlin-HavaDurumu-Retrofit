package com.mrcaracal.havadurumumrc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SimpleAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.mrcaracal.havadurumumrc.R
import com.mrcaracal.havadurumumrc.model.WeatherApiModel
import com.mrcaracal.havadurumumrc.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.favorites_activity.*
import kotlinx.android.synthetic.main.hourly_activity.*
import kotlinx.android.synthetic.main.hourly_item.*
import retrofit2.Converter
import java.util.Date

class HourlyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hourly_activity)
        val cityName = intent.getStringExtra("cityName")
        val position = intent.getIntExtra("position", 0)
        val viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewmodel.refreshData(cityName!!)
        var data: WeatherApiModel? = null
        var adapter:  SimpleAdapter? = null
        val fromMapKey = arrayOf("h_time_text", "h_temp_text", "h_image_icon")
        val toLayoutId = intArrayOf(R.id.h_time_text, R.id.h_temp_text, R.id.h_image_icon)
        var list: List<HashMap<String, String>> = ArrayList()
        viewmodel.weather_data.observe(this, Observer {
            data = it
            it?.forecast?.forecastday?.get(position)?.hour?.forEach() { h ->
                val hm = HashMap<String, String>()
                hm["h_time_text"] = h.time.substring(11, 16)
                hm["h_temp_text"] = h.tempC.toString() + "°C"
                hm["h_image_icon"] = "https:" + h.condition.icon
                list += hm
                adapter = SimpleAdapter(this, list, R.layout.hourly_item, fromMapKey, toLayoutId)
                adapter?.setViewBinder { view, data, _ ->
                    when (view.id) {
                        R.id.h_image_icon -> {
                            val iconUrl = data as String
                            Glide.with(this).load(iconUrl).into(view as ImageView)
                            true
                        }
                        else -> false
                    }
                }
                hourly_list.adapter = adapter;
            }
        })


    }
}