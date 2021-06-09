package com.ait.ui.standard.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ait.domain.model.ForecastedWeatherInfo
import com.ait.ui.standard.R
import com.ait.ui.standard.adapter.viewholder.DetailsViewHolder
import com.ait.ui.standard.adapter.viewholder.HourlyWeatherViewHolder
import com.ait.ui.standard.model.HourTemperatureInfo
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
                val inflater =
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view = inflater.inflate(R.layout.details_section_layout, parent, false)
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
                holder.setContent(
                    currentWeather.temperature,
                    currentWeather.windSpeed,
                    currentWeather.humidity,
                    currentWeather.feelsLike
                )
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

private enum class Section {
    Details,
    HourlyWeather
}