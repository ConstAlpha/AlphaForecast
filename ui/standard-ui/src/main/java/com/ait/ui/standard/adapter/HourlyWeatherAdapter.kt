package com.ait.ui.standard.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ait.ui.standard.model.HourTemperatureInfo
import com.ait.ui.standard.view.HourlyWeatherItemView
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
        val view = HourlyWeatherItemView(parent.context)
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

private class HourlyWeatherItemViewHolder(itemView: HourlyWeatherItemView) :
    RecyclerView.ViewHolder(itemView) {

    fun setContent(temp: Int, date: Date, isSelected: Boolean, updateDateTime: (Date) -> Unit) {
        val cal = Calendar.getInstance()
        cal.time = date
        (itemView as HourlyWeatherItemView).setItemValues(
            temp,
            cal.get(Calendar.HOUR_OF_DAY),
            isSelected
        )

        itemView.setOnClickListener {
            updateDateTime(date)
        }
    }
}