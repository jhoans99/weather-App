package com.svalero.wheatherapp.data.datasource.services

import com.svalero.wheatherapp.data.model.response.CitiesWeatherResponse
import com.svalero.wheatherapp.data.model.response.WeatherLocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("search.json")
    suspend fun fetchCities(
        @Query("q") filterName: String
    ): Response<ArrayList<CitiesWeatherResponse>>

    @GET("forecast.json")
    suspend fun getWeatherByCity(
        @Query("q") cityWeather: String,
        @Query("days") days: String = "3"
    ): Response<WeatherLocationResponse>
}