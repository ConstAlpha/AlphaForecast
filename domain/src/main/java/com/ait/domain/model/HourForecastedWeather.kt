package com.ait.domain.model

import java.util.*

data class HourForecastedWeather (
    val date: Date,
    val temperature: Int,
    val feelsLike: Int,
    val pressure: Int,
    val humidity: Int,
    val clouds: Int,
    val windSpeed: Int,
    val precipitation: Int,
    val description: String
)