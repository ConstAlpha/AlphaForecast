package com.ait.data.openweather

import com.ait.data.openweather.model.OwCommonWeatherInfo
import com.ait.data.openweather.source.OpenWeather
import com.ait.data.utilities.toDayForecastedWeather
import com.ait.domain.ForecastRepository
import com.ait.domain.model.ForecastedWeatherInfo

internal class OpenWeatherRepository(private val openWeather: OpenWeather) : ForecastRepository {

    override fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): ForecastedWeatherInfo? {
        val response: OwCommonWeatherInfo? = openWeather
            .getWeatherForecast(latitude, longitude)
            .execute()
            ?.body()
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
