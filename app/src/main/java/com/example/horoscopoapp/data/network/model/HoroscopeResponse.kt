package com.example.horoscopoapp.data.network.model

import com.example.horoscopoapp.domain.model.HoroscopeModel
import com.google.gson.annotations.SerializedName

data class HoroscopeResponse(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("sign") val sign: String,
    @SerializedName("id") val id: Int
)
//Funcion extendida para mapear el objeto de la data
fun HoroscopeResponse.toDomain(): HoroscopeModel =
    HoroscopeModel(
        horoscope = this.horoscope,
        icon = this.icon,
        sign = this.sign
    )
