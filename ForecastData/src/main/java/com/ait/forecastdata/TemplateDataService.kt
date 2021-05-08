package com.ait.forecastdata

import com.ait.forecastdata.models.weather.ForecastedWeatherInfo

class TemplateDataService : IForecastService {
    override suspend fun getWeatherForecast(latitude: Double, longitude: Double): ForecastedWeatherInfo?  {
        return null
    }
}