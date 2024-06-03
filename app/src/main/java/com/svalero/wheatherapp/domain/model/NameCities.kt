package com.svalero.wheatherapp.domain.model

import com.svalero.wheatherapp.data.model.response.CitiesWeatherResponse

data class NameCities(
    val nameCity: String,
    val nameCountry: String
)
fun CitiesWeatherResponse.toDomain() = NameCities(
    nameCity = name,
    nameCountry = country
)
