package com.ait.data

import com.ait.data.openweather.AppIdProvider
import com.ait.data.openweather.OpenWeatherRepository
import com.ait.data.openweather.source.OpenWeather
import com.ait.data.template.TemplateDataRepository
import com.ait.domain.ForecastRepository
import com.ait.domain.model.ForecastSource
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastRepositoryFactory {
    fun getForecastService(
        appIdProvider: AppIdProvider,
        source: ForecastSource
    ): ForecastRepository {
        // TODO: Will be reworked later with Hilt
        return when (source) {
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

                OpenWeatherRepository(openWeather)
            }
            ForecastSource.CHTO_TO_ESHYO -> TemplateDataRepository()
        }
    }
}