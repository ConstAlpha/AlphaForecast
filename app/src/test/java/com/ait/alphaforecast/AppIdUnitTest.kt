package com.ait.alphaforecast

import android.content.res.AssetManager
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class AppIdUnitTest {
    lateinit var appIdProvider: OWAppIdProvider

    @MockK
    lateinit var assetManager: AssetManager

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        appIdProvider = OWAppIdProvider(assetManager)
    }

    @Test
    fun `Check getAppId correct behaviour`() {
        val expectedAppId = "qwertyuiop"
        val appIdInputStream = expectedAppId.byteInputStream()
        every { assetManager.open(any()) } returns appIdInputStream

        val appId = appIdProvider.getAppId()

        assertEquals(expectedAppId, appId)
        verify { assetManager.open(withArg { assertEquals(it, "OpenWeatherAppId.txt") }) }
    }
}