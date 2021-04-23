package com.mvvm.code.network

import com.mvvm.code.model.response.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */
interface ServiceApi {

    @GET("forecast")
    fun getWeather(@Query("q") city:String, @Query("appid") appId:String ): Observable<WeatherResponse>
}