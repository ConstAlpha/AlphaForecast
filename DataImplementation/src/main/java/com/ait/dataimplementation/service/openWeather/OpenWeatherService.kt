package com.ait.dataimplementation.service.openWeather

import com.ait.dataapi.ForecastService
import com.ait.dataapi.model.CurrentWeather
import com.ait.dataapi.model.DayForecastedWeather
import com.ait.dataapi.model.ForecastedWeatherInfo
import com.ait.dataimplementation.model.openWeather.OWCommonWeatherInfo
import com.ait.dataimplementation.model.openWeather.OWCurrentWeatherInfo
import com.ait.dataimplementation.model.openWeather.OWDayWeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class OpenWeatherService : ForecastService {
    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): ForecastedWeatherInfo? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val openWeatherService = retrofit.create(OpenWeather::class.java)

        val response: OWCommonWeatherInfo? = withContext(Dispatchers.IO) {
            openWeatherService
                .getWeatherForecast(latitude, longitude)
                .execute()
                ?.body()
        }
        if (response != null) {
            val currentWeather = convertToCurrentWeatherInfo(response.current)
            val dailyForecast = response.daily.map {
                convertToDayForecastedWeatherInfo(it)
            }
            return ForecastedWeatherInfo(
                currentWeather = currentWeather,
                forecast = dailyForecast
            )
        }
        return null
    }

    private fun convertToDayForecastedWeatherInfo(weather: OWDayWeatherInfo): DayForecastedWeather {
        return DayForecastedWeather(
            date = Date(weather.forecastedDateTime.toLong()),
            sunriseTime = Date(weather.sunrise.toLong()),
            sunsetTime = Date(weather.sunset.toLong()),
            temperatureMorning = weather.temperature.morning.toInt(),
            temperatureDay = weather.temperature.day.toInt(),
            temperatureEvening = weather.temperature.evening.toInt(),
            temperatureNight = weather.temperature.night.toInt(),
            humidity = weather.humidity,
            clouds = weather.clouds,
            windSpeed = weather.windSpeed.toInt()
        )
    }

    private fun convertToCurrentWeatherInfo(weather: OWCurrentWeatherInfo): CurrentWeather {
        return CurrentWeather(
            sunriseTime = Date(weather.sunrise.toLong()),
            sunsetTime = Date(weather.sunset.toLong()),
            temperature = weather.temperature.toInt(),
            humidity = weather.humidity,
            clouds = weather.clouds,
            windSpeed = weather.windSpeed.toInt()
        )
    }
}