package com.svalero.wheatherapp.data.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDTO(
    @SerializedName("last_updated_epoch")
    val lastUpdateEpoch: String,
    @SerializedName("last_updated")
    val lastUpdate: String,
    @SerializedName("temp_c")
    val temperatureCentigrade: String,
    @SerializedName("condition")
    val condition: ConditionDTO
)

data class ConditionDTO(
    @SerializedName("text")
    val text: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("code")
    val code: String
)
