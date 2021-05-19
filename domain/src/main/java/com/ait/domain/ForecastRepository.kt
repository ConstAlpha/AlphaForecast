package com.ait.domain

import com.ait.domain.model.ForecastedWeatherInfo

interface ForecastRepository {
    fun getWeatherForecast(latitude: Double, longitude: Double): ForecastedWeatherInfo?
}