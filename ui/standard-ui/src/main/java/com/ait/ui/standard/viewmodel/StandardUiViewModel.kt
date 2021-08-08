package com.ait.ui.standard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ait.domain.ForecastRepository
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
    val timeDescriptor = MutableLiveData(TimeDescriptor(TimeOfDay.DAY, 0))

    init {
        viewModelScope.launch { loadForecast() }
    }

    fun changeDayTime(date: Date) {
        weatherInfo.value?.let {
            val sunrise: Long
            val sunset: Long
            if (date > it.currentWeather.sunsetTime) {
                sunrise = it.currentWeather.sunriseTime.time + MILLISECONDS_IN_DAY
                sunset = it.currentWeather.sunsetTime.time + MILLISECONDS_IN_DAY
            } else {
                sunrise = it.currentWeather.sunriseTime.time
                sunset = it.currentWeather.sunsetTime.time
            }
            val sunPercent = (date.time - sunrise).toDouble() / (sunset - sunrise) * 100

            val timeOfDay: TimeOfDay = when {
                sunPercent > -10 && sunPercent < 20 -> TimeOfDay.MORNING
                sunPercent > 20 && sunPercent < 80 -> TimeOfDay.DAY
                sunPercent > 80 && sunPercent < 110 -> TimeOfDay.EVENING
                else -> TimeOfDay.NIGHT
            }
            this.timeDescriptor.value = TimeDescriptor(timeOfDay, sunPercent.toInt())
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

    companion object {
        private const val MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000L
    }
}