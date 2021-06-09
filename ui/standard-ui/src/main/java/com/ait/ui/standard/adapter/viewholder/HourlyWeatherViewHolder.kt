package com.ait.ui.standard.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ait.ui.standard.adapter.HourlyWeatherAdapter
import com.ait.ui.standard.model.HourTemperatureInfo
import java.util.*

class HourlyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setContent(
        temperatureInfo: List<HourTemperatureInfo>,
        updateDateTime: (Date) -> Unit
    ) {
        val recyclerView = itemView as RecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = HourlyWeatherAdapter(temperatureInfo, updateDateTime)
    }
}