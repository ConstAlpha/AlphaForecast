package com.ait.alphaforecast

import android.os.Bundle
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

        lifecycleScope.launch(Dispatchers.Main) {
            val info = WeatherForecastManager(OWAppIdProvider(application), ForecastSource.OPEN_WEATHER)
                .getWeatherInfo(56.300560, 43.847348) // Sortirovka mode
            println(info)
        }
    }
}