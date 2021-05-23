package com.ait.ui.standard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ait.ui.common.TimeOfDay

class StandardUiViewModel : ViewModel() {

    val dayTime = MutableLiveData(TimeOfDay.DAY)

    fun changeDayTime(dayTime: TimeOfDay) {
        this.dayTime.value = dayTime
    }
}