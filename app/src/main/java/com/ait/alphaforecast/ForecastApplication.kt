package com.ait.alphaforecast

import android.app.Application
import com.ait.data.ForecastRepositoryFactory
import com.ait.data.openweather.AppIdProvider
import com.ait.domain.model.ForecastSource
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
        single { ForecastSource.OPEN_WEATHER }
        single { ForecastRepositoryFactory() }
        single {
            get(ForecastRepositoryFactory::class.java).getForecastService(get(), get())
        }
    }

    private fun viewModelModule() = module {
        viewModel { StandardUiViewModel(get()) }
    }
}