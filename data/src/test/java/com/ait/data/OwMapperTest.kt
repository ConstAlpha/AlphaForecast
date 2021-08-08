package com.ait.data

import com.ait.data.openweather.model.OwCurrentWeatherInfo
import com.ait.data.openweather.model.OwDayTemperature
import com.ait.data.openweather.model.OwDayWeatherInfo
import com.ait.data.openweather.utilities.OwMapper
import org.junit.Assert.assertEquals
import org.junit.Test

internal class OwMapperTest {
    private val mapper = OwMapper()

    @Test
    fun `Check convert to DayForecastedWeather`() {
        val owDayWeatherInfo = createOwDayWeatherInfo()
        val dayForecastedWeather = mapper.convertToDayForecastedWeather(owDayWeatherInfo)

        assertEquals(owDayWeatherInfo.forecastedDateTime * 1000, dayForecastedWeather.date.time)
        assertEquals(owDayWeatherInfo.sunrise * 1000, dayForecastedWeather.sunriseTime.time)
        assertEquals(owDayWeatherInfo.sunset * 1000, dayForecastedWeather.sunsetTime.time)
        assertEquals(
            owDayWeatherInfo.temperature.morning.toInt(),
            dayForecastedWeather.temperatureMorning
        )
        assertEquals(owDayWeatherInfo.temperature.day.toInt(), dayForecastedWeather.temperatureDay)
        assertEquals(
            owDayWeatherInfo.temperature.evening.toInt(),
            dayForecastedWeather.temperatureEvening
        )
        assertEquals(
            owDayWeatherInfo.temperature.night.toInt(),
            dayForecastedWeather.temperatureNight
        )
        assertEquals(owDayWeatherInfo.humidity, dayForecastedWeather.humidity)
        assertEquals(owDayWeatherInfo.clouds, dayForecastedWeather.clouds)
        assertEquals(owDayWeatherInfo.windSpeed.toInt(), dayForecastedWeather.windSpeed)
    }

    @Test
    fun `Check convert to CurrentWeather`() {
        val owCurrentWeatherInfo = createOwCurrentWeatherInfo()
        val currentWeather = mapper.convertToCurrentWeather(owCurrentWeatherInfo)

        assertEquals(owCurrentWeatherInfo.sunrise * 1000, currentWeather.sunriseTime.time)
        assertEquals(owCurrentWeatherInfo.sunset * 1000, currentWeather.sunsetTime.time)
        assertEquals(owCurrentWeatherInfo.temperature.toInt(), currentWeather.temperature)
        assertEquals(owCurrentWeatherInfo.humidity, currentWeather.humidity)
        assertEquals(owCurrentWeatherInfo.clouds, currentWeather.clouds)
        assertEquals(owCurrentWeatherInfo.windSpeed.toInt(), currentWeather.windSpeed)
    }

    private fun createOwDayWeatherInfo(): OwDayWeatherInfo {
        return OwDayWeatherInfo(
            forecastedDateTime = 1,
            sunrise = 2,
            sunset = 3,
            moonrise = 4,
            moonset = 5,
            moonPhase = 6.0,
            temperature = createOwDayTemperature(),
            pressure = 7,
            humidity = 8,
            windSpeed = 9.0,
            weather = listOf(),
            clouds = 10
        )
    }

    private fun createOwCurrentWeatherInfo(): OwCurrentWeatherInfo {
        return OwCurrentWeatherInfo(
            forecastedDateTime = 1,
            sunrise = 2,
            sunset = 3,
            temperature = 4.0,
            pressure = 5,
            humidity = 6,
            windSpeed = 7.0,
            weather = listOf(),
            clouds = 8,
            feelsLike = 9.0
        )
    }

    private fun createOwDayTemperature(): OwDayTemperature {
        return OwDayTemperature(
            morning = 1.0,
            day = 2.0,
            evening = 3.0,
            night = 4.0,
            min = 1.0,
            max = 4.0
        )
    }
}