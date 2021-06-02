package com.ait.alphaforecast

import android.app.Application
import com.ait.data.ForecastServiceFactory
import com.ait.data.openweather.AppIdProvider
import com.ait.data.openweather.OpenWeatherRepository
import com.ait.data.openweather.source.OpenWeatherService
import com.ait.data.openweather.utilities.OwServiceSettings
import com.ait.domain.ForecastRepository
import com.ait.ui.standard.viewmodel.StandardUiViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ForecastApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDependencies()
    }

    private fun initDependencies() {
        startKoin {
            androidContext(this@ForecastApplication)
            modules(dataModule(), viewModelModule())
        }
    }

    private fun dataModule() = module {
        single<AppIdProvider> { OWAppIdProvider(assets) }
        single { ForecastServiceFactory() }
        single {
            OwServiceSettings().getForecastSourceData(get(AppIdProvider::class.java))
        }
        single {
            get(ForecastServiceFactory::class.java).getForecastService<OpenWeatherService>(get())
        }
        single<ForecastRepository> { OpenWeatherRepository(get()) }
    }

    private fun viewModelModule() = module {
        viewModel { StandardUiViewModel(get()) }
    }
}