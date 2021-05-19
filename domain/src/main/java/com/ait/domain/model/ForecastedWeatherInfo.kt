package com.ait.domain.model

data class ForecastedWeatherInfo (
    val currentWeather: CurrentWeather,
    val forecast: List<DayForecastedWeather>
)