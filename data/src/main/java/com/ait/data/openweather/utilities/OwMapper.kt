package com.ait.data.openweather.utilities

import com.ait.data.openweather.model.OwCommonWeatherInfo
import com.ait.data.openweather.model.OwCurrentWeatherInfo
import com.ait.data.openweather.model.OwDayWeatherInfo
import com.ait.data.openweather.model.OwHourWeatherInfo
import com.ait.domain.model.CurrentWeather
import com.ait.domain.model.DayForecastedWeather
import com.ait.domain.model.ForecastedWeatherInfo
import com.ait.domain.model.HourForecastedWeather
import java.util.*

internal class OwMapper {

    fun convertToForecastedWeatherInfo(commonWeatherInfo: OwCommonWeatherInfo): ForecastedWeatherInfo {
        return ForecastedWeatherInfo(
            currentWeather = convertToCurrentWeather(commonWeatherInfo.current),
            daily = commonWeatherInfo.daily.map { convertToDayForecastedWeather(it) },
            hourly = commonWeatherInfo.hourly.map { convertToHourForecastedWeather(it) }
        )
    }

    fun convertToDayForecastedWeather(dayWeatherInfo: OwDayWeatherInfo): DayForecastedWeather {
        return DayForecastedWeather(
            date = Date(dayWeatherInfo.forecastedDateTime * 1000),
            sunriseTime = Date(dayWeatherInfo.sunrise * 1000),
            sunsetTime = Date(dayWeatherInfo.sunset * 1000),
            temperatureMorning = dayWeatherInfo.temperature.morning.toInt(),
            temperatureDay = dayWeatherInfo.temperature.day.toInt(),
            temperatureEvening = dayWeatherInfo.temperature.evening.toInt(),
            temperatureNight = dayWeatherInfo.temperature.night.toInt(),
            humidity = dayWeatherInfo.humidity,
            clouds = dayWeatherInfo.clouds,
            windSpeed = dayWeatherInfo.windSpeed.toInt()
        )
    }

    fun convertToHourForecastedWeather(hourWeatherInfo: OwHourWeatherInfo): HourForecastedWeather {
        return HourForecastedWeather(
            date = Date(hourWeatherInfo.forecastedDateTime * 1000),
            temperature = hourWeatherInfo.temperature.toInt(),
            feelsLike = hourWeatherInfo.feelsLike.toInt(),
            pressure = hourWeatherInfo.pressure,
            humidity = hourWeatherInfo.humidity,
            clouds = hourWeatherInfo.clouds,
            windSpeed = hourWeatherInfo.windSpeed.toInt(),
            precipitation = hourWeatherInfo.precipitation.toInt(),
            description = hourWeatherInfo.weather.first().description
        )
    }

    fun convertToCurrentWeather(currentWeatherInfo: OwCurrentWeatherInfo): CurrentWeather {
        return CurrentWeather(
            sunriseTime = Date(currentWeatherInfo.sunrise * 1000),
            sunsetTime = Date(currentWeatherInfo.sunset * 1000),
            temperature = currentWeatherInfo.temperature.toInt(),
            humidity = currentWeatherInfo.humidity,
            clouds = currentWeatherInfo.clouds,
            windSpeed = currentWeatherInfo.windSpeed.toInt(),
            feelsLike = currentWeatherInfo.feelsLike.toInt()
        )
    }
}