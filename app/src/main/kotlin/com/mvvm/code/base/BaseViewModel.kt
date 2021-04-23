package com.mvvm.code.base

import android.arch.lifecycle.ViewModel
import com.mvvm.code.injection.component.DaggerViewModelInjector
import com.mvvm.code.injection.component.ViewModelInjector
import com.mvvm.code.injection.module.NetworkModule
import com.mvvm.code.ui.weather.WeatherAdapterViewModel
import com.mvvm.code.ui.weather.WeatherViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is WeatherViewModel -> injector.inject(this)
            is WeatherAdapterViewModel -> injector.inject(this)
        }
    }
}