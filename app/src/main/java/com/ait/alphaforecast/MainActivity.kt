package com.ait.alphaforecast

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ait.dataapi.model.ForecastSource
import com.ait.dataimplementation.WeatherForecastManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.start_button)

        startButton.setOnClickListener {
            val forecastManager = WeatherForecastManager(ForecastSource.OPEN_WEATHER)
            lifecycleScope.launch(Dispatchers.Main) {
                getWeatherInfo(forecastManager, 56.19, 44.0)
            }
        }
    }

    private suspend fun getWeatherInfo(
        forecastManager: com.ait.dataimplementation.WeatherForecastManager,
        latitude: Double,
        longitude: Double
    ) {
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val resultTextView = findViewById<TextView>(R.id.result_text_view)
        resultTextView.text = ""
        progressBar.visibility = View.VISIBLE

        val info = forecastManager.getWeatherInfo(latitude, longitude)

        resultTextView.text = info?.currentWeather.toString()
        progressBar.visibility = View.GONE
    }
}