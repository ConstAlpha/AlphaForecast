package com.ait.dataimplementation

import com.ait.dataapi.AppIdProvider
import com.ait.dataapi.ForecastService
import com.ait.dataapi.model.ForecastSource
import com.ait.dataapi.model.ForecastedWeatherInfo
import com.ait.dataimplementation.service.openWeather.OpenWeather
import com.ait.dataimplementation.service.openWeather.OpenWeatherService
import com.ait.dataimplementation.service.template.TemplateDataService
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherForecastManager(private val appIdProvider: AppIdProvider, private val source: ForecastSource) {

    suspend fun getWeatherInfo(latitude: Double, longitude: Double): ForecastedWeatherInfo? {

        // TODO: Will be reworked later with Hilt
        val forecastService: ForecastService = when (source) {
            ForecastSource.OPEN_WEATHER -> {
                val client = OkHttpClient().newBuilder()

                val appId: String = appIdProvider.getAppId()
                client.addInterceptor { chain ->
                    var request: Request = chain.request()
                    val url: HttpUrl = request
                        .url()
                        .newBuilder()
                        .addQueryParameter("appid", appId)
                        .build()
                    request = request
                        .newBuilder()
                        .url(url)
                        .build()
                    chain.proceed(request)
                }

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build()
                val openWeather = retrofit.create(OpenWeather::class.java)

                OpenWeatherService(openWeather)
            }
            ForecastSource.CHTO_TO_ESHYO -> TemplateDataService()
        }

        return forecastService.getWeatherForecast(latitude, longitude)
    }
}