package com.example.horoscopoapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopoapp.core.network.ResultType
import com.example.horoscopoapp.data.network.model.HoroscopeResponse
import com.example.horoscopoapp.domain.GetHoroscopeUseCase
import com.example.horoscopoapp.domain.dto.HoroscopeDTO
import com.example.horoscopoapp.domain.model.HoroscopeModel
import com.example.horoscopoapp.ui.details.model.DetailsUIState
import com.example.horoscopoapp.ui.details.model.DetailsUIState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.sign


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getHoroscopeUseCase: GetHoroscopeUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow<DetailsUIState>(Loading)
    val uiState: StateFlow<DetailsUIState> = _uiState
    fun getHoroscope() {
        viewModelScope.launch {
            getHoroscopeUseCase(HoroscopeDTO("capricorn"))
                .collect { horoscope: ResultType<HoroscopeModel> ->
                    //Los flows se deben coletiar
                    when(horoscope){
                        is ResultType.Error -> _uiState.value = Error(horoscope.msg.orEmpty())
                        is ResultType.Success -> _uiState.value = Success(horoscope.data!!)
                    }
                }
        }
    }

}