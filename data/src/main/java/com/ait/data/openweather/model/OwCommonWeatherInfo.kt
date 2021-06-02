package com.ait.data.openweather.model

import com.google.gson.annotations.SerializedName

data class OwCommonWeatherInfo (
    @SerializedName("lat") val latitude: Double?,
    @SerializedName("lon") val longitude: Double?,
    @SerializedName("timezone") val timeZone: String,
    @SerializedName("timezone_offset") val timeZoneOffset: Int,
    @SerializedName("current") val current: OwCurrentWeatherInfo,
    @SerializedName("daily") val daily: List<OwDayWeatherInfo>
)