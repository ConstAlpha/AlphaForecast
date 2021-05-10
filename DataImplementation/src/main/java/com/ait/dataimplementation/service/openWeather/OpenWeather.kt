package com.ait.dataimplementation.service.openWeather

import com.ait.dataimplementation.model.openWeather.OWCommonWeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface OpenWeather {
    @GET("onecall?units=metric&exclude=minutely,hourly,alerts&appid=fb5b8d7fc64091a58eb0247d3913b6ae")
    fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<OWCommonWeatherInfo>
}