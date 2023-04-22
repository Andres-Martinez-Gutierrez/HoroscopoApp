package com.example.horoscopoapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopoapp.domain.GetHoroscopeUseCase
import com.example.horoscopoapp.ui.details.model.DetailsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getHoroscopeUseCase: GetHoroscopeUseCase
) : ViewModel() {


    private val _iuState = MutableStateFlow<DetailsUIState>(DetailsUIState.Loading)
    val iuState: StateFlow<DetailsUIState> = _iuState
    fun getHoroscope() {
        viewModelScope.launch {
            getHoroscopeUseCase()
                .collect {
                    //Los flows se deben coletiar
                    if (it != null) {
                        //Aqui ya esta el horoscopo bueno
                        _iuState.value = DetailsUIState.Success(it)

                    }
                }
        }
    }

}