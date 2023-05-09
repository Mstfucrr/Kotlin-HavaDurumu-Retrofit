package com.mrcaracal.havadurumumrc.service

import com.mrcaracal.havadurumumrc.model.*
import com.mrcaracal.havadurumumrc.model.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    //http://api.openweathermap.org/data/2.5/weather?q=bingöl&APPID=04a42b96398abc8e4183798ed22f9485

    //@GET("data/2.5/weather?&units=metric&APPID=04a42b96398abc8e4183798ed22f9485")
    @GET("forecast.json?key=d1398cba8a894feb9f7180821232602&days=10&aqi=no")
    fun getData(
        @Query("q") cityName: String
    ): Single<WeatherApiModel>

}