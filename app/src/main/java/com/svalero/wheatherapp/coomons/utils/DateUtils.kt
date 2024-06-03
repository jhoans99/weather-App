package com.svalero.wheatherapp.coomons.utils

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {

    fun getNameByEpoch(dateStr: String): String {
        val dayNameFormatter = DateTimeFormatter.ofPattern("EEEE")
        val dateString = dateStr
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, formatter)
        return date.format(dayNameFormatter).replaceFirstChar { if (it.isLowerCase()) it.titlecase(
            Locale("es")
        ) else it.toString() }
    }
}