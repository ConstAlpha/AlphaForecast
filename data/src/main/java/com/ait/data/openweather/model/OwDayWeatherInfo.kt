package com.ait.data.openweather.model

import com.google.gson.annotations.SerializedName

data class OwDayWeatherInfo(
    @SerializedName("dt") val forecastedDateTime: Long,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long,
    @SerializedName("moonrise") val moonrise: Long,
    @SerializedName("moonset") val moonset: Long,
    @SerializedName("moon_phase") val moonPhase: Double,
    @SerializedName("temp") val temperature: OwDayTemperature,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("weather") val weather: List<OwWeather>,
    @SerializedName("clouds") val clouds: Int
)