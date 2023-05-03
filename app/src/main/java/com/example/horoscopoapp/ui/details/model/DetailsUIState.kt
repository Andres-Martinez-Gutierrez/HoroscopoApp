package com.example.horoscopoapp.ui.details.model

import com.example.horoscopoapp.domain.model.HoroscopeModel

sealed class DetailsUIState {
    object Loading : DetailsUIState()
    data class Success(val horoscopeModel: HoroscopeModel): DetailsUIState()
    data class Error(val message: String): DetailsUIState()
}