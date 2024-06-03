package com.svalero.wheatherapp.data.model

import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("lat")
    val latitude: String,
    @SerializedName("lon")
    val longitude: String,
    @SerializedName("tz_id")
    val tzId: String,
    @SerializedName("localtime_epoch")
    val localtimeEpoch: String,
    @SerializedName("localtime")
    val localTime: String
)
