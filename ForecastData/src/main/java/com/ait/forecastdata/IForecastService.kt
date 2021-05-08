package com.ait.forecastdata

import com.ait.forecastdata.models.weather.ForecastedWeatherInfo

interface IForecastService {
    suspend fun getWeatherForecast(latitude: Double, longitude: Double): ForecastedWeatherInfo?
}