package com.ait.ui.standard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ait.domain.ForecastRepository
import com.ait.domain.model.CurrentWeather
import com.ait.domain.model.ForecastedWeatherInfo
import com.ait.ui.common.TimeDescriptor
import com.ait.ui.common.TimeOfDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    object Error : UiState()
}

class StandardUiViewModel(
    private val repository: ForecastRepository
) : ViewModel() {

    private val uiStateFlow = MutableStateFlow<UiState>(UiState.Success)
    val isLoading = uiStateFlow.map { it == UiState.Loading }.asLiveData()
    val weatherInfo = MutableLiveData<ForecastedWeatherInfo>()
    val timeDescriptor = MutableLiveData(TimeDescriptor.default())

    init {
        viewModelScope.launch { loadForecast() }
    }

    fun changeDayTime(date: Date) {
        weatherInfo.value?.currentWeather?.let {

            val sunPercent = it.sunPosition(date)
            val moonPercent = it.moonPosition(date)

            val timeOfDay: TimeOfDay = when {
                sunPercent > -10 && sunPercent < 20 -> TimeOfDay.MORNING
                sunPercent > 20 && sunPercent < 80 -> TimeOfDay.DAY
                sunPercent > 80 && sunPercent < 110 -> TimeOfDay.EVENING
                else -> TimeOfDay.NIGHT
            }
            this.timeDescriptor.value = TimeDescriptor(
                timeOfDay = timeOfDay,
                sunPosition = sunPercent.toInt(),
                moonPosition = moonPercent.toInt(),
                clouds = it.clouds
            )
        }
    }

    private suspend fun loadForecast() {
        // Nizhny Novgorod
        uiStateFlow.emit(UiState.Loading)
        repository.getWeatherForecast(56.1943, 44.0007)?.let { info ->
            setUpCurrentWeather(info)

            uiStateFlow.emit(UiState.Success)
        } ?: kotlin.run {
            uiStateFlow.emit(UiState.Error)
        }
    }

    private fun setUpCurrentWeather(weatherInfo: ForecastedWeatherInfo) {
        this.weatherInfo.value = weatherInfo
    }

    private fun CurrentWeather.sunPosition(date: Date): Double {
        val sunrise: Long
        val sunset: Long
        if (date > sunsetTime) {
            sunrise = sunriseTime.time + MILLISECONDS_IN_DAY
            sunset = sunsetTime.time + MILLISECONDS_IN_DAY
        } else {
            sunrise = sunriseTime.time
            sunset = sunsetTime.time
        }
        return (date.time - sunrise).toDouble() / (sunset - sunrise) * 100
    }

    private fun CurrentWeather.moonPosition(date: Date): Double {
        val nextDaySunrise = sunriseTime.time + MILLISECONDS_IN_DAY
        val sunset = sunsetTime.time

        return (date.time - sunset).toDouble() / (nextDaySunrise - sunset) * 100
    }

    companion object {
        private const val MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000L
    }
}