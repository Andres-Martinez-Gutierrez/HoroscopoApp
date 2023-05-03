package com.example.horoscopoapp.data.network

import android.os.RemoteException
import com.example.horoscopoapp.core.network.ResultType
import com.example.horoscopoapp.data.network.model.toDomain
import com.example.horoscopoapp.domain.dto.HoroscopeDTO
import com.example.horoscopoapp.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val api: HoroscopeApi) {
    fun getHoroscope(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> = flow{

        try {
            val response = api.getHoroscope(horoscopeDTO.sign, horoscopeDTO.date, horoscopeDTO.lang)
            if (response.isSuccessful){
                response.body()?.let {horoscopeResponse ->
                    emit(ResultType.Success(horoscopeResponse.toDomain()))
                }

            }else{
                val msg = when(response.code()){
                    400 -> "Bad Request"
                    401 -> "Unauthorized"
                    403 -> "Forbidden"
                    404 -> "Not Found"
                    500 -> "Internal Server Error"
                    503 -> "Service Unavailable"
                    else -> "Error generic"
                }
                emit(ResultType.Error(msg))
            }

        }catch (e:Exception){
            val msg = when(e){
                is SecurityException -> "La solicitud requiera permisos que no se hayan otorgado"
                is IOException -> "Se encuentren problemas de lectura y escritura desde el disco"
                is IllegalStateException -> "El servicio de Health Connect no esté disponible o la solicitud no sea una construcción válida"
                is RemoteException -> "se produjo errores en el servicio subyacente al que se conecta el SDK o durante la comunicación con él"
                else -> "Exception Generica"
            }
            emit(ResultType.Error(msg))
        }

    }
}