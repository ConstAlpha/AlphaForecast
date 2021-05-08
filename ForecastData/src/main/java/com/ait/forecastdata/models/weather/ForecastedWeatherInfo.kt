package com.ait.forecastdata.models.weather

data class ForecastedWeatherInfo (
    val currentWeather: CurrentWeather,
    val forecast: List<DayForecastedWeather>
)