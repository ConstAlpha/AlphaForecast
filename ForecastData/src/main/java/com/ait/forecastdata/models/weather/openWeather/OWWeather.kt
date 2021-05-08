package com.ait.forecastdata.models.weather.openWeather

import com.google.gson.annotations.SerializedName

data class OWWeather (
    @SerializedName("main") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val iconName: String,
)