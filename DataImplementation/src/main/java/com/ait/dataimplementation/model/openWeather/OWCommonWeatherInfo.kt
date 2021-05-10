package com.ait.dataimplementation.model.openWeather

import com.google.gson.annotations.SerializedName

internal data class OWCommonWeatherInfo (
    @SerializedName("lat") val latitude: Double?,
    @SerializedName("lon") val longitude: Double?,
    @SerializedName("timezone") val timeZone: String,
    @SerializedName("timezone_offset") val timeZoneOffset: Int,
    @SerializedName("current") val current: OWCurrentWeatherInfo,
    @SerializedName("daily") val daily: List<OWDayWeatherInfo>
)