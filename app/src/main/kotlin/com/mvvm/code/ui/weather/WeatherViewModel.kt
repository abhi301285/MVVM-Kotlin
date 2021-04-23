package com.mvvm.code.ui.weather

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import com.mvvm.code.R
import com.mvvm.code.model.response.WeatherResponse
import com.mvvm.code.network.ServiceApi
import com.mvvm.code.utils.APP_ID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel() : com.mvvm.code.base.BaseViewModel() {
    @Inject
    lateinit var serviceApi: ServiceApi
   val weatherAdapter: WeatherAdapter = WeatherAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadWeatherData("") }

    private lateinit var subscription: Disposable

    fun loadWeather(city:String) {
        loadWeatherData(city)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    /**
     * Loading weather related to city
     * @param city: This is the city name which we are using to get the
     * weather details
     */
    private fun loadWeatherData(city:String) {

        onRetrieveWeatherListStart()
        onRetrieveWeatherListSuccess()
        subscription = (serviceApi.getWeather(city, APP_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<WeatherResponse>() {
                    override fun onNext(response: WeatherResponse) {
                        onRetrieveWeatherListSuccess(response)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Throwable","Throwable ${e.localizedMessage}")
                        onRetrieveWeatherListError()
                    }

                    override fun onComplete() {
                        onRetrieveWeatherListFinish()
                        Log.e("onComplete", "onComplete")
                    }
                }))
    }

    private fun onRetrieveWeatherListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveWeatherListFinish() {
        loadingVisibility.value = View.GONE
    }

    /**
     * Updating the adapter with the weather list
     * @param response : Response of the API in which we are getting the
     * weather details of the city
     */
    private fun onRetrieveWeatherListSuccess(response: WeatherResponse) {
        weatherAdapter.updatePostList(response.list!!)
    }

    private fun onRetrieveWeatherListSuccess() {
        weatherAdapter.updatePostList(arrayListOf())
    }

    private fun onRetrieveWeatherListError() {
        loadingVisibility.value = View.GONE
        errorMessage.value = R.string.post_error
    }
}