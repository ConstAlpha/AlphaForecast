package com.ait.ui.standard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ait.ui.standard.R
import com.ait.ui.standard.adapter.viewholder.HourlyWeatherItemViewHolder
import com.ait.ui.standard.model.HourTemperatureInfo
import java.util.*

class HourlyWeatherAdapter(
    private val hourlyTemperature: List<HourTemperatureInfo>,
    private val updateDateTime: (Date) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedItemPosition: Int = -1

    override fun getItemCount(): Int {
        return hourlyTemperature.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.hourly_weather_item_layout, parent, false)
        return HourlyWeatherItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as HourlyWeatherItemViewHolder
        val tempInfo = hourlyTemperature[position]
        viewHolder.setContent(
            tempInfo.temperature,
            tempInfo.date,
            position == selectedItemPosition
        ) {
            updateSelectedItemView(position, tempInfo.date)
        }
    }

    private fun updateSelectedItemView(position: Int, date: Date) {
        val previousSelectedItemPosition = selectedItemPosition
        selectedItemPosition = position
        notifyItemChanged(previousSelectedItemPosition)
        notifyItemChanged(selectedItemPosition)
        updateDateTime(date)
    }
}