package com.ait.data

import com.ait.data.openweather.AppIdProvider
import com.ait.data.openweather.utilities.OwServiceSettings
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

internal class ForecastServiceFactoryTest {
    @MockK
    lateinit var appIdProvider: AppIdProvider

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `Check ForecastServiceFactory correct behaviour`() {
        every { appIdProvider.getAppId() } returns ""

        val forecastServiceFactory = ForecastServiceFactory()
        val sourceData = OwServiceSettings().getForecastSourceData(appIdProvider)
        val service =
            forecastServiceFactory.getForecastService(sourceData)

        assertNotNull(service)
    }
}