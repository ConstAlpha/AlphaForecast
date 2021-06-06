package com.ait.domain.model

data class ForecastedWeatherInfo (
    val currentWeather: CurrentWeather,
    val daily: List<DayForecastedWeather>,
    val hourly: List<HourForecastedWeather>
)