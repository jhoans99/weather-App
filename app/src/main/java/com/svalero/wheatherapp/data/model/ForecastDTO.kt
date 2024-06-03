package com.svalero.wheatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastDTO(
    @SerializedName("forecastday")
    val forecastDay: ArrayList<ForecastDayDataDTO>
)

data class ForecastDayDataDTO(
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Long,
    @SerializedName("day")
    val day: DayDTO
)

data class DayDTO(
    @SerializedName("maxtemp_c")
    val maxTemperature: String,
    @SerializedName("mintemp_c")
    val minTemperature: String,
    @SerializedName("avgtemp_c")
    val avgTemperature: String,
    @SerializedName("condition")
    val conditionDTO: ConditionDTO
)
