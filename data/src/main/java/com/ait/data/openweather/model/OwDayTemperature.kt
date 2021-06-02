package com.ait.data.openweather.model

import com.google.gson.annotations.SerializedName

data class OwDayTemperature(
    @SerializedName("morn") val morning: Double,
    @SerializedName("day") val day: Double,
    @SerializedName("eve") val evening: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double
)