package com.svalero.wheatherapp.data.model.response

import com.google.gson.annotations.SerializedName

data class CitiesWeatherResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("country")
    val country: String
)