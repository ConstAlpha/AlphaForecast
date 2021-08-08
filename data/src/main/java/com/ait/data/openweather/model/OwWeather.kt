package com.ait.data.openweather.model

import com.google.gson.annotations.SerializedName

data class OwWeather(
    @SerializedName("main") val title: String,
    @SerializedName("description") val description: String
)