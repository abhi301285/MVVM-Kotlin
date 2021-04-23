package com.mvvm.code.ui.weather

import android.arch.lifecycle.MutableLiveData
import com.mvvm.code.model.Weather

class WeatherAdapterViewModel : com.mvvm.code.base.BaseViewModel() {
    private val weatherDate = MutableLiveData<String>()
    private val weatherCloudStatus = MutableLiveData<String>()
    private val temp = MutableLiveData<String>()

    fun bind(weather: Weather) {
        weatherDate.value = weather.dt_txt
        weatherCloudStatus.value = weather.weather!![0].main + ": " + weather.weather!![0].description
        temp.value = weather.main!!.temp.toString()
    }

    fun getDate(): MutableLiveData<String> {
        return weatherDate
    }

    fun getCloudStatus(): MutableLiveData<String> {
        return weatherCloudStatus
    }

    fun getTemp(): MutableLiveData<String> {
        return temp
    }
}