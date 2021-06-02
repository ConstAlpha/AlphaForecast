package com.ait.data.openweather

import com.ait.data.openweather.source.OpenWeatherService
import com.ait.data.openweather.utilities.OwMapper
import com.ait.domain.ForecastRepository
import retrofit2.HttpException

class OpenWeatherRepository(private val openWeatherService: OpenWeatherService) :
    ForecastRepository {

    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ) = try {
        val mapper = OwMapper()
        openWeatherService.getWeatherForecast(latitude, longitude)
            .let { mapper.convertToForecastedWeatherInfo(it) }
    } catch (e: HttpException) {
        null
    }
}
