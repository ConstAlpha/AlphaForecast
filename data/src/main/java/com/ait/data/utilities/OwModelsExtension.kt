package com.ait.data.utilities

import com.ait.domain.model.CurrentWeather
import com.ait.domain.model.DayForecastedWeather
import com.ait.data.openweather.model.OwCurrentWeatherInfo
import com.ait.data.openweather.model.OwDayWeatherInfo
import java.util.*

internal fun OwDayWeatherInfo.toDayForecastedWeather(): DayForecastedWeather {
    return DayForecastedWeather(
        date = Date(this.forecastedDateTime.toLong()),
        sunriseTime = Date(this.sunrise.toLong()),
        sunsetTime = Date(this.sunset.toLong()),
        temperatureMorning = this.temperature.morning.toInt(),
        temperatureDay = this.temperature.day.toInt(),
        temperatureEvening = this.temperature.evening.toInt(),
        temperatureNight = this.temperature.night.toInt(),
        humidity = this.humidity,
        clouds = this.clouds,
        windSpeed = this.windSpeed.toInt()
    )
}

internal fun OwCurrentWeatherInfo.toDayForecastedWeather(): CurrentWeather {
    return CurrentWeather(
        sunriseTime = Date(this.sunrise.toLong()),
        sunsetTime = Date(this.sunset.toLong()),
        temperature = this.temperature.toInt(),
        humidity = this.humidity,
        clouds = this.clouds,
        windSpeed = this.windSpeed.toInt()
    )
}