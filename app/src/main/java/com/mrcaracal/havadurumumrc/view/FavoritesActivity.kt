package com.mrcaracal.havadurumumrc.view

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.mrcaracal.havadurumumrc.R
import com.mrcaracal.havadurumumrc.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.favorite_city_item.*
import kotlinx.android.synthetic.main.favorites_activity.*


class FavoritesActivity : AppCompatActivity() {
    private lateinit var favoriteCitiesList: List<String>
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorites_activity)
        sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        val favoriteCities = sharedPreferences.getStringSet("favoriteCities", HashSet<String>())!!
        favoriteCitiesList = favoriteCities.toList()
        loadFavoriteCitiesWeatherData()
        //loadFav()
    }

    @SuppressLint("SetTextI18n")
    private fun loadFavoriteCitiesWeatherData() {
        var adapter:  SimpleAdapter? = null
        val fromMapKey = arrayOf("f_cityname_text", "f_temp_text", "img_icon","f_hour_text","f_condition_text")
        val toLayoutId = intArrayOf(R.id.f_cityname_text, R.id.f_temp_text, R.id.img_icon,R.id.f_hour_text,R.id.f_condition_text)
        var list: List<HashMap<String, String>> = ArrayList()
        favoriteCitiesList.forEach() {
            viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            viewmodel.refreshData(it)
            }
        viewmodel.weather_data.observe(this, Observer { weatherData ->
            val hm = HashMap<String, String>()
            hm["f_cityname_text"] = weatherData.location.name
            hm["f_temp_text"] = weatherData.current.tempC.toString() + "°C"
            hm["img_icon"] = "https:" + weatherData.current.condition.icon
            hm["f_hour_text"] = weatherData.location.localtime
            hm["f_condition_text"] = weatherData.current.condition.text
            list += hm
            adapter = SimpleAdapter(this, list, R.layout.favorite_city_item, fromMapKey, toLayoutId)
            adapter?.setViewBinder { view, data, _ ->
                when (view.id) {
                    R.id.img_icon -> {
                        val iconUrl = data as String
                        Glide.with(this).load(iconUrl).into(view as ImageView)
                        true
                    }
                    else -> false
                }
            }
            favorites_list.adapter = adapter;
        })
        }

    }


