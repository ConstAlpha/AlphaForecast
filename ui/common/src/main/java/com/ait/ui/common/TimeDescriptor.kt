package com.ait.ui.common

data class TimeDescriptor(
    val timeOfDay: TimeOfDay,
    val sunPosition: Int, // position on firmament in percent
)
