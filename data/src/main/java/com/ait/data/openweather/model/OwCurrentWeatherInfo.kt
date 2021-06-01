package com.ait.data.openweather.model

import com.google.gson.annotations.SerializedName

internal data class OwCurrentWeatherInfo (
    @SerializedName("dt") val forecastedDateTime: Long,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("weather") val weather: List<OwWeather>,
    @SerializedName("clouds") val clouds: Int
)