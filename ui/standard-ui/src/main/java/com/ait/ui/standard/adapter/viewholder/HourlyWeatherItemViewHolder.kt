package com.ait.ui.standard.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ait.ui.standard.R
import java.util.*

class HourlyWeatherItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.hourly_weather_icon)
    private val currentHourTextView: TextView = itemView.findViewById(R.id.current_hour)
    private val tempTextView: TextView = itemView.findViewById(R.id.hourly_temperature)

    fun setContent(temp: Int, date: Date, isSelected: Boolean, updateDateTime: (Date) -> Unit) {
        val cal = Calendar.getInstance()
        cal.time = date

        // TODO set an icon to imageView
        currentHourTextView.text = cal.get(Calendar.HOUR_OF_DAY).toString()
        tempTextView.text = temp.toString()
        itemView.background =
            if (isSelected) itemView.context.getDrawable(R.drawable.hourly_weather_selected_item_background) else null

        itemView.setOnClickListener {
            updateDateTime(date)
        }
    }
}