package com.ait.dataimplementation.service.openWeather

import com.ait.dataapi.ForecastService
import com.ait.dataapi.model.ForecastedWeatherInfo
import com.ait.dataimplementation.model.openWeather.OWCommonWeatherInfo
import com.ait.dataimplementation.utilities.toDayForecastedWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class OpenWeatherService (private val openWeather: OpenWeather) : ForecastService {

    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): ForecastedWeatherInfo? {
        val response: OWCommonWeatherInfo? = withContext(Dispatchers.IO) {
            openWeather
                .getWeatherForecast(latitude, longitude)
                .execute()
                ?.body()
        }
        if (response != null) {
            val currentWeather = response.current.toDayForecastedWeather()
            val dailyForecast = response.daily.map {
                it.toDayForecastedWeather()
            }
            return ForecastedWeatherInfo(
                currentWeather = currentWeather,
                forecast = dailyForecast
            )
        }
        return null
    }
}
