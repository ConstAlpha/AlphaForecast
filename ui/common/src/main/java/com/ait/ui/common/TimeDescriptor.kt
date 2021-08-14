package com.ait.ui.common

data class TimeDescriptor(
    val timeOfDay: TimeOfDay,
    val sunPosition: Int, // position on firmament in percent
    val moonPosition: Int,
    val clouds: Int,
) {
    companion object {
        fun default() = TimeDescriptor(
            timeOfDay = TimeOfDay.DAY,
            sunPosition = 0,
            moonPosition = 0,
            clouds = 0,
        )
    }
}
