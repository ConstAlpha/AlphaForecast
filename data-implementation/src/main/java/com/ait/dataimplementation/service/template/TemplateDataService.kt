package com.ait.dataimplementation.service.template

import com.ait.dataapi.ForecastService
import com.ait.dataapi.model.ForecastedWeatherInfo

class TemplateDataService : ForecastService {
    override suspend fun getWeatherForecast(latitude: Double, longitude: Double): ForecastedWeatherInfo?  {
        return null
    }
}