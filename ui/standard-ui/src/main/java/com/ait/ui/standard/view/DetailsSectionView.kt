package com.ait.ui.standard.view

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.ait.ui.standard.R

class DetailsSectionView(
    context: Context
) : ConstraintLayout(context) {

    private val temperatureItemView: DetailsItemView
    private val feelsLikeItemView: DetailsItemView
    private val windSpeedItemView: DetailsItemView
    private val precipitationItemView: DetailsItemView

    init {
        inflate(context, R.layout.details_section_layout, this)

        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        temperatureItemView = findViewById(R.id.temperature_view)
        feelsLikeItemView = findViewById(R.id.feels_like_view)
        windSpeedItemView = findViewById(R.id.wind_speed_view)
        precipitationItemView = findViewById(R.id.precipitation_view)
    }

    fun setContent(temperature: Int, windSpeed: Int, precipitation: Int, feelsLike: Int) {
        temperatureItemView.setIcon(context.getDrawable(R.drawable.ic_thermostat))
        temperatureItemView.setTitle("Temperature")
        temperatureItemView.setValue("${temperature}°")

        feelsLikeItemView.setIcon(context.getDrawable(R.drawable.ic_feels_like))
        feelsLikeItemView.setTitle("Feels like")
        feelsLikeItemView.setValue("${feelsLike}°")

        windSpeedItemView.setIcon(context.getDrawable(R.drawable.ic_wind))
        windSpeedItemView.setTitle("Wind speed")
        windSpeedItemView.setValue("$windSpeed km/h")

        precipitationItemView.setIcon(context.getDrawable(R.drawable.ic_umbrella))
        precipitationItemView.setTitle("Precipitation")
        precipitationItemView.setValue("${precipitation}%")
    }
}