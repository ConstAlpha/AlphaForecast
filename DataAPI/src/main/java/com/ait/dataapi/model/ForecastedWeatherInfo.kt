package com.ait.dataapi.model

data class ForecastedWeatherInfo (
    val currentWeather: CurrentWeather,
    val forecast: List<DayForecastedWeather>
)