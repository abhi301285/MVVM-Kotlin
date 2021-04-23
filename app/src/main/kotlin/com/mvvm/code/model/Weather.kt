package com.mvvm.code.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.mvvm.code.model.response.BaseResponse

class Weather  {

    var clouds:Clouds? = null
    var coord: Coordinate? = null
    var weather: List<CloudDesc>? = null
    var base: String? = null
    var dt_txt: String? = null
    var main: Temp? = null
    var visibility: Double? = 0.0
    var wind: Wind? = null
    var sys: Sys? = null
    var timezone: Long? = 0
    var dt: Long? = 0
    @field:PrimaryKey
    var id: Long? = 0
    var name: String? = null
    var pop: Double? = 0.0

}