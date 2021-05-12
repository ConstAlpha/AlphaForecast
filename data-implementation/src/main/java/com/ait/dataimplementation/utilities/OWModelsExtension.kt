package com.ait.dataimplementation.utilities

import com.ait.dataapi.model.CurrentWeather
import com.ait.dataapi.model.DayForecastedWeather
import com.ait.dataimplementation.model.openWeather.OWCurrentWeatherInfo
import com.ait.dataimplementation.model.openWeather.OWDayWeatherInfo
import java.util.*

internal fun OWDayWeatherInfo.toDayForecastedWeather(): DayForecastedWeather {
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

internal fun OWCurrentWeatherInfo.toDayForecastedWeather(): CurrentWeather {
    return CurrentWeather(
        sunriseTime = Date(this.sunrise.toLong()),
        sunsetTime = Date(this.sunset.toLong()),
        temperature = this.temperature.toInt(),
        humidity = this.humidity,
        clouds = this.clouds,
        windSpeed = this.windSpeed.toInt()
    )
}