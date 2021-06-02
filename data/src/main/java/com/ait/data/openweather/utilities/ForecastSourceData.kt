package com.ait.data.openweather.utilities

import okhttp3.Interceptor

data class ForecastSourceData<T> (
    val baseUrl: String,
    val type: Class<T>,
    val interceptors: List<Interceptor>
)