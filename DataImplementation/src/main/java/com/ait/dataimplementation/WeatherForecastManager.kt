package com.ait.dataimplementation

import com.ait.dataapi.ForecastService
import com.ait.dataapi.model.ForecastSource
import com.ait.dataapi.model.ForecastedWeatherInfo
import com.ait.dataimplementation.service.openWeather.OpenWeatherService
import com.ait.dataimplementation.service.template.TemplateDataService

class WeatherForecastManager(private val source: ForecastSource) {
    suspend fun getWeatherInfo(latitude: Double, longitude: Double): ForecastedWeatherInfo? {
        // TODO: Will be reworked later with Hilt
        val forecastService: ForecastService = when (source) {
            ForecastSource.OPEN_WEATHER -> OpenWeatherService()
            ForecastSource.CHTO_TO_ESHYO -> TemplateDataService()
        }

        return forecastService.getWeatherForecast(latitude, longitude)
    }
}