package com.ait.ui.standard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ait.domain.ForecastRepository
import com.ait.domain.model.CurrentWeather
import com.ait.ui.common.TimeOfDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    object Error : UiState()
}

class StandardUiViewModel(
    private val repository: ForecastRepository
) : ViewModel() {

    private val uiStateFlow = MutableStateFlow<UiState>(UiState.Success)
    val isLoading = uiStateFlow.map { it == UiState.Loading}.asLiveData()

    val dayTime = MutableLiveData(TimeOfDay.DAY)

    init {
        viewModelScope.launch { loadForecast() }
    }

    fun changeDayTime(dayTime: TimeOfDay) {
        this.dayTime.value = dayTime
    }

    private suspend fun loadForecast() {
        // New York
        uiStateFlow.emit(UiState.Loading)
        repository.getWeatherForecast(40.730610, -73.935242)?.let { info ->
            setUpCurrentWeather(info.currentWeather)

            uiStateFlow.emit(UiState.Success)
        } ?: kotlin.run {
            uiStateFlow.emit(UiState.Error)
        }
    }

    private fun setUpCurrentWeather(currentWeather: CurrentWeather) {

    }
}