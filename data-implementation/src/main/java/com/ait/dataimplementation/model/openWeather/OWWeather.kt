package com.ait.dataimplementation.model.openWeather

import com.google.gson.annotations.SerializedName

internal data class OWWeather (
    @SerializedName("main") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val iconName: String,
)