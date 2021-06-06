package com.ait.ui.standard.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ait.domain.model.ForecastedWeatherInfo
import com.ait.ui.common.TimeOfDay
import com.ait.ui.standard.model.HourTemperatureInfo
import com.ait.ui.standard.view.DetailsSectionView
import java.util.*

class ForecastDataAdapter(
    private val weatherInfo: ForecastedWeatherInfo,
    private val updateDateTime: (Date) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return Section.values().size
    }

    override fun getItemViewType(position: Int): Int {
        return Section.values()[position].ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val section = Section.values().first { it.ordinal == viewType }

        when (section) {
            Section.Details -> {
                val view = DetailsSectionView(parent.context)
                return DetailsViewHolder(view)
            }
            Section.HourlyWeather -> {
                val recyclerView = RecyclerView(parent.context)
                return HourlyWeatherViewHolder(recyclerView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentWeather = weatherInfo.currentWeather

        when (holder) {
            is DetailsViewHolder -> {
                holder.setContent(currentWeather.temperature, currentWeather.windSpeed, 20, currentWeather.feelsLike)
            }
            is HourlyWeatherViewHolder -> {
                holder.setContent(
                    weatherInfo.hourly.take(24)
                        .map { HourTemperatureInfo(it.date, it.temperature) }, updateDateTime
                )
            }
        }
    }
}

class DetailsViewHolder(itemView: DetailsSectionView) : RecyclerView.ViewHolder(itemView) {
    fun setContent(temperature: Int, windSpeed: Int, precipitation: Int, feelsLike: Int) {
        (itemView as DetailsSectionView).setContent(
            temperature,
            windSpeed,
            precipitation,
            feelsLike
        )
    }
}

private enum class Section {
    Details,
    HourlyWeather
}

private class HourlyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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