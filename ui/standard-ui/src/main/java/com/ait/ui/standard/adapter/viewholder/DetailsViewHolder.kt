package com.ait.ui.standard.adapter.viewholder

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ait.ui.standard.R

class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val temperatureView: View = itemView.findViewById(R.id.temperature_view)
    private val feelsLikeView: View = itemView.findViewById(R.id.feels_like_view)
    private val windSpeedView: View = itemView.findViewById(R.id.wind_speed_view)
    private val precipitationView: View = itemView.findViewById(R.id.precipitation_view)

    fun setContent(temperature: Int, windSpeed: Int, humidity: Int, feelsLike: Int) {
        val context = itemView.context
        setItemViewContent(
            temperatureView,
            context.getDrawable(R.drawable.ic_thermostat),
            context.getString(R.string.temperature),
            "${temperature}°"
        )
        setItemViewContent(
            feelsLikeView,
            context.getDrawable(R.drawable.ic_feels_like),
            context.getString(R.string.feels_like),
            "${feelsLike}°"
        )
        setItemViewContent(
            windSpeedView,
            context.getDrawable(R.drawable.ic_wind),
            context.getString(R.string.wind_speed),
            "$windSpeed km/h"
        )
        setItemViewContent(
            precipitationView,
            context.getDrawable(R.drawable.ic_umbrella),
            context.getString(R.string.humidity),
            "${humidity}%"
        )
    }

    private fun setItemViewContent(rootView: View, icon: Drawable?, title: String, value: String) {
        val itemIcon = rootView.findViewById<ImageView>(R.id.item_icon)
        itemIcon.setImageDrawable(icon)
        val itemTitle = rootView.findViewById<TextView>(R.id.item_title)
        itemTitle.text = title
        val itemValue = rootView.findViewById<TextView>(R.id.item_value)
        itemValue.text = value
    }
}