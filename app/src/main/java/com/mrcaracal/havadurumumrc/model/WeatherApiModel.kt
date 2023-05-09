package com.mrcaracal.havadurumumrc.model


import com.google.gson.annotations.SerializedName

data class WeatherApiModel(
    val location: Location,
    val current: Current,
    val forecast: Forecast,
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    @SerializedName("tz_id")
    val tzId: String,
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Long,
    val localtime: String,
)

data class Current(
    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Long,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    @SerializedName("is_day")
    val isDay: Long,
    val condition: Condition,
    @SerializedName("wind_mph")
    val windMph: Double,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_degree")
    val windDegree: Long,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("pressure_mb")
    val pressureMb: Long,
    @SerializedName("pressure_in")
    val pressureIn: Double,
    @SerializedName("precip_mm")
    val precipMm: Double,
    @SerializedName("precip_in")
    val precipIn: Long,
    val humidity: Long,
    val cloud: Long,
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    @SerializedName("vis_km")
    val visKm: Long,
    @SerializedName("vis_miles")
    val visMiles: Long,
    val uv: Long,
    @SerializedName("gust_mph")
    val gustMph: Double,
    @SerializedName("gust_kph")
    val gustKph: Double,
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Long,
)

data class Forecast(
    val forecastday: List<Forecastday>,
)

data class Forecastday(
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Long,
    val day: Day,
    val astro: Astro,
    val hour: List<Hour>,
)

data class Day(
    @SerializedName("maxtemp_c")
    val maxtempC: Double,
    @SerializedName("maxtemp_f")
    val maxtempF: Double,
    @SerializedName("mintemp_c")
    val mintempC: Double,
    @SerializedName("mintemp_f")
    val mintempF: Double,
    @SerializedName("avgtemp_c")
    val avgtempC: Double,
    @SerializedName("avgtemp_f")
    val avgtempF: Double,
    @SerializedName("maxwind_mph")
    val maxwindMph: Double,
    @SerializedName("maxwind_kph")
    val maxwindKph: Double,
    @SerializedName("totalprecip_mm")
    val totalprecipMm: Double,
    @SerializedName("totalprecip_in")
    val totalprecipIn: Double,
    @SerializedName("totalsnow_cm")
    val totalsnowCm: Long,
    @SerializedName("avgvis_km")
    val avgvisKm: Double,
    @SerializedName("avgvis_miles")
    val avgvisMiles: Long,
    val avghumidity: Long,
    @SerializedName("daily_will_it_rain")
    val dailyWillItRain: Long,
    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Long,
    @SerializedName("daily_will_it_snow")
    val dailyWillItSnow: Long,
    @SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: Long,
    val condition: Condition2,
    val uv: Long,
)

data class Condition2(
    val text: String,
    val icon: String,
    val code: Long,
)

data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    @SerializedName("moon_phase")
    val moonPhase: String,
    @SerializedName("moon_illumination")
    val moonIllumination: String,
    @SerializedName("is_moon_up")
    val isMoonUp: Long,
    @SerializedName("is_sun_up")
    val isSunUp: Long,
)

data class Hour(
    @SerializedName("time_epoch")
    val timeEpoch: Long,
    val time: String,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    @SerializedName("is_day")
    val isDay: Long,
    val condition: Condition3,
    @SerializedName("wind_mph")
    val windMph: Double,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_degree")
    val windDegree: Long,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("pressure_mb")
    val pressureMb: Long,
    @SerializedName("pressure_in")
    val pressureIn: Double,
    @SerializedName("precip_mm")
    val precipMm: Double,
    @SerializedName("precip_in")
    val precipIn: Double,
    val humidity: Long,
    val cloud: Long,
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    @SerializedName("windchill_c")
    val windchillC: Double,
    @SerializedName("windchill_f")
    val windchillF: Double,
    @SerializedName("heatindex_c")
    val heatindexC: Double,
    @SerializedName("heatindex_f")
    val heatindexF: Double,
    @SerializedName("dewpoint_c")
    val dewpointC: Double,
    @SerializedName("dewpoint_f")
    val dewpointF: Double,
    @SerializedName("will_it_rain")
    val willItRain: Long,
    @SerializedName("chance_of_rain")
    val chanceOfRain: Long,
    @SerializedName("will_it_snow")
    val willItSnow: Long,
    @SerializedName("chance_of_snow")
    val chanceOfSnow: Long,
    @SerializedName("vis_km")
    val visKm: Long,
    @SerializedName("vis_miles")
    val visMiles: Long,
    @SerializedName("gust_mph")
    val gustMph: Double,
    @SerializedName("gust_kph")
    val gustKph: Double,
    val uv: Long,
)

data class Condition3(
    val text: String,
    val icon: String,
    val code: Long,
)
