package com.ait.data

import com.ait.data.openweather.model.OwCommonWeatherInfo
import com.ait.data.openweather.model.OwCurrentWeatherInfo
import com.ait.data.openweather.model.OwDayTemperature
import com.ait.data.openweather.model.OwDayWeatherInfo
import com.ait.data.openweather.source.OpenWeatherService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OpenWeatherRepositoryTest {
    @MockK
    lateinit var openWeatherService: OpenWeatherService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Check getWeatherForecast correct behaviour`() {
        val commonWeatherInfo = createOwCommonWeatherInfo()
        coEvery {
            openWeatherService.getWeatherForecast(any(), any())
        } returns commonWeatherInfo

        val actualCommonWeatherInfo = runBlocking {
            openWeatherService.getWeatherForecast(0.0, 0.0)
        }

        assertEquals(commonWeatherInfo, actualCommonWeatherInfo)
    }

    private fun createOwCommonWeatherInfo(): OwCommonWeatherInfo {
        return OwCommonWeatherInfo(
            latitude = 0.0,
            longitude = 0.0,
            timeZone = "",
            timeZoneOffset = 1,
            current = createOwCurrentWeatherInfo(),
            daily = listOf(createOwDayWeatherInfo())
        )
    }

    private fun createOwDayWeatherInfo(): OwDayWeatherInfo {
        return OwDayWeatherInfo(
            forecastedDateTime = 1,
            sunrise = 2,
            sunset = 3,
            moonrise = 4,
            moonset = 4,
            moonPhase = 5.0,
            temperature = createOwDayTemperature(),
            pressure = 5,
            humidity = 6,
            windSpeed = 7.0,
            weather = listOf(),
            clouds = 8
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
            clouds = 8
        )
    }
}