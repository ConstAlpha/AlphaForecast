package com.ait.forecastdata.models.weather.openWeather

import com.google.gson.annotations.SerializedName

data class OWCommonWeatherInfo (
    @SerializedName("lat") val latitude: Double?,
    @SerializedName("lon") val longitude: Double?,
    @SerializedName("timezone") val timeZone: String,
    @SerializedName("timezone_offset") val timeZoneOffset: Int,
    @SerializedName("current") val current: OWCurrentWeatherInfo,
    @SerializedName("daily") val daily: List<OWDayWeatherInfo>
)