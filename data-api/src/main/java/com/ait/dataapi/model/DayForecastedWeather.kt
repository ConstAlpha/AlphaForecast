package com.ait.dataapi.model

import java.util.*

data class DayForecastedWeather (
    val date: Date,
    val sunriseTime: Date,
    val sunsetTime: Date,
    val temperatureMorning: Int,
    val temperatureDay: Int,
    val temperatureEvening: Int,
    val temperatureNight: Int,
    val humidity: Int,
    val clouds: Int,
    val windSpeed: Int
)