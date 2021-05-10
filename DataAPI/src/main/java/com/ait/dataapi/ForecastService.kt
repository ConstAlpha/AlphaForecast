package com.ait.dataapi

import com.ait.dataapi.model.ForecastedWeatherInfo

interface ForecastService {
    suspend fun getWeatherForecast(latitude: Double, longitude: Double): ForecastedWeatherInfo?
}