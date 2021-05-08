package com.ait.forecastdata.models.weather

import java.util.*

data class CurrentWeather (
    val sunriseTime: Date,
    val sunsetTime: Date,
    val temperature: Int,
    val humidity: Int,
    val clouds: Int,
    val windSpeed: Int
)