package com.ait.forecastdata

import com.ait.forecastdata.models.weather.ForecastSource
import com.ait.forecastdata.models.weather.ForecastedWeatherInfo

class WeatherForecastManager(private val source: ForecastSource) {
    suspend fun getWeatherInfo(latitude: Double, longitude: Double): ForecastedWeatherInfo? {
        val forecastService: IForecastService = when (source) {
            ForecastSource.OPEN_WEATHER -> OpenWeatherService()
            ForecastSource.CHTO_TO_ESHYO -> TemplateDataService()
        }

        return forecastService.getWeatherForecast(latitude, longitude)
    }
}