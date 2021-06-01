package com.ait.data

import com.ait.domain.model.ForecastSourceData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastServiceFactory {

    fun <T> getForecastService(
        sourceData: ForecastSourceData<T>
    ): T {
        val client = OkHttpClient().newBuilder().also {
            for (interceptor in sourceData.interceptors) {
                it.addInterceptor(interceptor)
            }
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(sourceData.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
        return retrofit.create(sourceData.type)
    }
}