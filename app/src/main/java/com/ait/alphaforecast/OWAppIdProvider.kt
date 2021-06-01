package com.ait.alphaforecast

import android.content.res.AssetManager
import com.ait.data.openweather.AppIdProvider
import java.io.BufferedReader

class OWAppIdProvider(private val assetManager: AssetManager): AppIdProvider {
    override fun getAppId(): String {
        val bufferReader = assetManager
            .open("OpenWeatherAppId.txt")
            .bufferedReader()

        return bufferReader.use {
            it.readText()
        };
    }
}