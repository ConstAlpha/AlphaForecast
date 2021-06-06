package com.ait.ui.standard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ait.domain.ForecastRepository
import com.ait.domain.model.ForecastedWeatherInfo
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
    val dayTime = MutableLiveData(TimeOfDay.DAY)

    init {
        viewModelScope.launch { loadForecast() }
    }

    fun changeDayTime(date: Date) {
        val selectedHourlyWeatherInfo = weatherInfo.value?.hourly?.first { it.date == date }

        val cal = Calendar.getInstance()
        cal.time = date
        // TODO Need to select TimeOfDay depending on sunrise and sunset
        val timeOfDay: TimeOfDay = when (cal.get(Calendar.HOUR_OF_DAY)) {
            in 0..6 -> TimeOfDay.NIGHT
            in 7..12 -> TimeOfDay.MORNING
            in 13..18 -> TimeOfDay.DAY
            in 19..23 -> TimeOfDay.EVENING
            else -> TimeOfDay.NIGHT
        }
        if (this.dayTime.value != timeOfDay) {
            this.dayTime.value = timeOfDay
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
}