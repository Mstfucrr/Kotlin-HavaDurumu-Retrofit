package com.mrcaracal.havadurumumrc.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.mrcaracal.havadurumumrc.R.*
import com.mrcaracal.havadurumumrc.model.Forecastday
import com.mrcaracal.havadurumumrc.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashSet


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel: MainViewModel

    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor

    @SuppressLint("MutatingSharedPrefs")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()

        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val cName = GET.getString("cityName", "bingöl")?.toLowerCase()
        edt_city_name.setText(cName)
        viewmodel.refreshData(cName!!)

        getLiveData()

        swipe_refresh_layout.setOnRefreshListener {
            ll_data.visibility = View.GONE
            tv_error.visibility = View.GONE
            pb_loading.visibility = View.GONE

            val cityName = GET.getString("cityName", cName)?.toLowerCase()
            edt_city_name.setText(cityName)
            viewmodel.refreshData(cityName!!)
            swipe_refresh_layout.isRefreshing = false
        }

        img_search_city.setOnClickListener {
            val cityName = edt_city_name.text.toString()
            SET.putString("cityName", cityName)
            SET.apply()
            viewmodel.refreshData(cityName)
            getLiveData()
            Log.i(TAG, "onCreate: $cityName")
            // SharedPreferences nesnesini oluşturun

            // Favori ekleme/dişe ekleme butonunu ayarlayın
            if (isFavorite(cityName.toLowerCase())) {
                FavoriteButton.setImageResource(drawable.ic_star_filled)
            } else {
                FavoriteButton.setImageResource(drawable.ic_star_empty)
            }
        }
        btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("lat", viewmodel.weather_data.value?.location?.lat)
            intent.putExtra("lon", viewmodel.weather_data.value?.location?.lon)
            startActivity(intent)
        }

        btnFavorites.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }


        FavoriteButton.setOnClickListener {
            val cityName = edt_city_name.text.toString().lowercase(Locale.getDefault())
            val favoriteCities: MutableSet<String> =
                GET.getStringSet("favoriteCities", HashSet<String>()) as MutableSet<String>
            if (isFavorite(cityName)) {
                favoriteCities.remove(cityName)
                FavoriteButton.setImageResource(drawable.ic_star_empty)
            } else {
                favoriteCities.add(cityName)
                FavoriteButton.setImageResource(drawable.ic_star_filled)
            }
            //val editor: SharedPreferences.Editor = sharedPreferences.edit()
            //favoriteCities kümelerini kaydedin
            SET.putStringSet("favoriteCities", favoriteCities).apply()
            SET.putString("cityName", cityName.toUpperCase()).apply()
            SET.putString("cityName", cityName).apply()
        }

        btnForecast.setOnClickListener {
            val intent = Intent(this, ForecastActivity::class.java)
            intent.putExtra("cityName", cName.toString())
            startActivity(intent)

        }

    }

    private fun isFavorite(cityName: String): Boolean {
        val favoriteCities: Set<String> =
            GET.getStringSet("favoriteCities", HashSet<String>()) as Set<String>
        return !(favoriteCities.isEmpty() || !favoriteCities.contains(cityName))
    }

    @SuppressLint("SetTextI18n")
    private fun getLiveData() {

        viewmodel.weather_data.observe(this, Observer { data ->
            data?.let {
                ll_data.visibility = View.VISIBLE

                tv_city_code.text = data.location.country
                tv_city_name.text = data.location.name

                Glide.with(this)
                    .load("https:" + data.current.condition.icon)
                    .into(img_weather_pictures)

                tv_degree.text = data.current.tempC.toString() + "°C"

                tv_humidity.text = data.current.humidity.toString() + "%"
                tv_wind_speed.text = data.current.windKph.toString()
                tv_lat.text = data.location.lat.toString()
                tv_lon.text = data.location.lon.toString()

            }
        })

        viewmodel.weather_error.observe(this, Observer { error ->
            error?.let {
                if (error) {
                    tv_error.visibility = View.VISIBLE
                    pb_loading.visibility = View.GONE
                    ll_data.visibility = View.GONE
                } else {
                    tv_error.visibility = View.GONE
                }
            }
        })

        viewmodel.weather_loading.observe(this, Observer { loading ->
            loading?.let {
                if (loading) {
                    pb_loading.visibility = View.VISIBLE
                    tv_error.visibility = View.GONE
                    ll_data.visibility = View.GONE
                } else {
                    pb_loading.visibility = View.GONE
                }
            }
        })

    }

}