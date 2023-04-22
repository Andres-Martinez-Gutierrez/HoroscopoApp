package com.example.horoscopoapp.ui.details.model

import com.example.horoscopoapp.data.network.model.HoroscopeResponse

sealed class DetailsUIState {
    object Loading : DetailsUIState()
    data class Success(private val horoscopeResponse: HoroscopeResponse): DetailsUIState()
    data class Error(val message: String): DetailsUIState()
}