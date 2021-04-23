package com.mvvm.code.injection.component

import com.mvvm.code.injection.module.NetworkModule
import com.mvvm.code.ui.weather.WeatherAdapterViewModel
import com.mvvm.code.ui.weather.WeatherViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified WeatherViewModel.
     * @param weatherViewModel WeatherViewModel in which to inject the dependencies
     */
    fun inject(weatherViewModel: WeatherViewModel)
    fun inject(weatherAdapterViewModel: WeatherAdapterViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}