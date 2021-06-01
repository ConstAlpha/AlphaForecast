package com.ait.data.openweather

import com.ait.data.openweather.source.OpenWeather
import com.ait.data.utilities.toDayForecastedWeather
import com.ait.domain.ForecastRepository
import com.ait.domain.model.ForecastedWeatherInfo
import retrofit2.HttpException

internal class OpenWeatherRepository(private val openWeather: OpenWeather) : ForecastRepository {

    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ) = try {
        openWeather.getWeatherForecast(latitude, longitude).let { response ->
            // TODO: add mapping method for converting OwCommonWeatherInfo into ForecastedWeatherInfo
            ForecastedWeatherInfo(
                currentWeather = response.current.toDayForecastedWeather(),
                forecast = response.daily.map { it.toDayForecastedWeather() }
            )
        }
    } catch (e: HttpException) {
        null
    }
}
