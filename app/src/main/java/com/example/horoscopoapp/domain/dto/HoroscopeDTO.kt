package com.example.horoscopoapp.domain.dto

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class HoroscopeDTO(
    val sign: String,
    val date: String = getTodayDateFormatted(),
    val lang: String = "es"
)

private fun getTodayDateFormatted(): String {
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(currentDate)
}