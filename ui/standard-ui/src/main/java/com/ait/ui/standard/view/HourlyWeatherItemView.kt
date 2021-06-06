package com.ait.ui.standard.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ait.ui.standard.R

class HourlyWeatherItemView(
    context: Context
) : ConstraintLayout(context) {
    private val imageView: ImageView
    private val currentHourTextView: TextView
    private val tempTextView: TextView

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.hourly_weather_item_layout, this, true)
        imageView = findViewById(R.id.hourly_weather_icon)
        currentHourTextView = findViewById(R.id.current_hour)
        tempTextView = findViewById(R.id.hourly_temperature)
    }

    fun setItemValues(temperature: Int, currentHour: Int, isSelected: Boolean) {
        currentHourTextView.text = currentHour.toString()
        tempTextView.text = temperature.toString()
        background =
            if (isSelected) context.getDrawable(R.drawable.hourly_weather_selected_item_background) else null
    }
}