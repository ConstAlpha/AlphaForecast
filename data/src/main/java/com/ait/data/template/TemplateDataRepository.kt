package com.ait.data.template

import com.ait.domain.ForecastRepository
import com.ait.domain.model.ForecastedWeatherInfo

class TemplateDataRepository : ForecastRepository {
    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): ForecastedWeatherInfo? {
        return null
    }
}