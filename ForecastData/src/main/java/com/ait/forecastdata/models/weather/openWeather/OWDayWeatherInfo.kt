package com.ait.forecastdata.models.weather.openWeather

import com.google.gson.annotations.SerializedName

data class OWDayWeatherInfo(
    @SerializedName("dt") val forecastedDateTime: Int,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: String,
    @SerializedName("moonrise") val moonrise: Int,
    @SerializedName("moonset") val moonset: Int,
    @SerializedName("moon_phase") val moonPhase: Double,
    @SerializedName("temp") val temperature: OWDayTemperature,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("weather") val weather: List<OWWeather>,
    @SerializedName("clouds") val clouds: Int
)