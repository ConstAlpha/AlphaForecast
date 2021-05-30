package com.ait.data.utilities

import com.ait.domain.model.CurrentWeather
import com.ait.domain.model.DayForecastedWeather
import com.ait.data.openweather.model.OwCurrentWeatherInfo
import com.ait.data.openweather.model.OwDayWeatherInfo
import java.util.*

internal fun OwDayWeatherInfo.toDayForecastedWeather(): DayForecastedWeather {
    return DayForecastedWeather(
        date = Date(this.forecastedDateTime * 1000),
        sunriseTime = Date(this.sunrise * 1000),
        sunsetTime = Date(this.sunset * 1000),
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
        sunriseTime = Date(this.sunrise * 1000),
        sunsetTime = Date(this.sunset * 1000),
        temperature = this.temperature.toInt(),
        humidity = this.humidity,
        clouds = this.clouds,
        windSpeed = this.windSpeed.toInt()
    )
}