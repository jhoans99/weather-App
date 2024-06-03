package com.svalero.wheatherapp.data.datasource.remote

import com.svalero.wheatherapp.coomons.utils.WeatherExpection
import com.svalero.wheatherapp.data.datasource.services.WeatherApiService
import com.svalero.wheatherapp.data.model.response.CitiesWeatherResponse
import com.svalero.wheatherapp.data.model.response.WeatherLocationResponse
import javax.inject.Inject

class WeatherDataSource @Inject constructor(
    private val weatherApiService: WeatherApiService
) {

    suspend fun fetchCities(name: String): ArrayList<CitiesWeatherResponse> {
        if(weatherApiService.fetchCities(name).isSuccessful) {
            return weatherApiService.fetchCities(name).body() ?: ArrayList()
        }else {
            throw WeatherExpection()
        }

    }

    suspend fun fetchWeatherByCity(nameCity: String): WeatherLocationResponse {
        if(weatherApiService.getWeatherByCity(nameCity).isSuccessful) {
            return weatherApiService.getWeatherByCity(nameCity).body()!!
        }else {
            throw WeatherExpection()
        }
    }
}