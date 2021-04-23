package com.mvvm.code.model.response

import com.mvvm.code.model.City
import com.mvvm.code.model.Weather

class WeatherResponse : BaseResponse() {
    var cnt:Int =0
    var list: List<Weather>? = null
    var city: City? = null
}