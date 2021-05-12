package com.ait.alphaforecast

import android.app.Application
import com.ait.dataapi.AppIdProvider

class OWAppIdProvider(private val application: Application): AppIdProvider {
    override fun getAppId(): String {
        val bufferReader = application.assets
            .open("OpenWeatherAppId.txt")
            .bufferedReader()

        return bufferReader.use {
            it.readText()
        };
    }
}