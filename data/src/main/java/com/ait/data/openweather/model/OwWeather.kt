package com.ait.data.openweather.model

import com.google.gson.annotations.SerializedName

internal data class OwWeather (
    @SerializedName("main") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val iconName: String,
)