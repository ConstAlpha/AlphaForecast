package com.ait.data.openweather.utilities

import com.ait.data.openweather.AppIdProvider
import com.ait.data.openweather.source.OpenWeatherService
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

class OwServiceSettings {
    fun getForecastSourceData(appIdProvider: AppIdProvider): ForecastSourceData<OpenWeatherService> {
        return ForecastSourceData(
            baseUrl = "https://api.openweathermap.org/data/2.5/",
            type = OpenWeatherService::class.java,
            interceptors = listOf(
                Interceptor { chain ->
                    val appId: String = appIdProvider.getAppId()
                    var request: Request = chain.request()
                    val url: HttpUrl = request
                        .url
                        .newBuilder()
                        .addQueryParameter("appid", appId)
                        .build()
                    request = request
                        .newBuilder()
                        .url(url)
                        .build()
                    chain.proceed(request)
                },
                HttpLoggingInterceptor().also { int ->
                    int.level = HttpLoggingInterceptor.Level.BODY
                }
            )
        )
    }
}