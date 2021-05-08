package com.ait.forecastdata

import com.ait.forecastdata.models.weather.openWeather.OWCommonWeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IOpenWeather {
    @GET("data/2.5/onecall?units=metric&exclude=minutely,hourly,alerts&appid=fb5b8d7fc64091a58eb0247d3913b6ae")
    fun getWeatherForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<OWCommonWeatherInfo>
}