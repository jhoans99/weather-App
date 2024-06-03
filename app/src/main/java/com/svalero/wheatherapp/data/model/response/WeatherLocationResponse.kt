package com.svalero.wheatherapp.data.model.response

import com.google.gson.annotations.SerializedName
import com.svalero.wheatherapp.data.model.CurrentWeatherDTO
import com.svalero.wheatherapp.data.model.ForecastDTO
import com.svalero.wheatherapp.data.model.LocationDTO

data class WeatherLocationResponse(
    @SerializedName("location")
    val location: LocationDTO,
    @SerializedName("current")
    val currentWeather: CurrentWeatherDTO,
    @SerializedName("forecast")
    val forecast: ForecastDTO
)


