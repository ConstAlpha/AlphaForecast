package com.ait.data.openweather.model

import com.google.gson.annotations.SerializedName

data class OwHourWeatherInfo(
    @SerializedName("dt") val forecastedDateTime: Long,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("weather") val weather: List<OwWeather>,
    @SerializedName("clouds") val clouds: Int,
    @SerializedName("pop") val precipitation: Double
)