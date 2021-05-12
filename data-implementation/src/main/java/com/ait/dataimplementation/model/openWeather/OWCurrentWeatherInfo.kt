package com.ait.dataimplementation.model.openWeather

import com.ait.dataapi.model.CurrentWeather
import com.google.gson.annotations.SerializedName
import java.util.*

internal data class OWCurrentWeatherInfo (
    @SerializedName("dt") val forecastedDateTime: Int,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: String,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("weather") val weather: List<OWWeather>,
    @SerializedName("clouds") val clouds: Int
)