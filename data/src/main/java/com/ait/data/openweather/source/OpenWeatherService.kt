package com.ait.data.openweather.source

import com.ait.data.openweather.model.OwCommonWeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("onecall?units=metric&exclude=minutely,hourly,alerts")
    suspend fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): OwCommonWeatherInfo
}