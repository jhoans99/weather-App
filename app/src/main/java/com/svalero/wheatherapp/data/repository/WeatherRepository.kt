package com.svalero.wheatherapp.data.repository

import com.svalero.wheatherapp.coomons.Result
import com.svalero.wheatherapp.data.datasource.remote.WeatherDataSource
import com.svalero.wheatherapp.data.model.response.WeatherLocationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherDataSource: WeatherDataSource
) {
    suspend fun fetchWeatherByCity(nameCity: String): Flow<Result<WeatherLocationResponse>> = flow {
        emit(Result.Loading)
        val response = weatherDataSource.fetchWeatherByCity(nameCity)
        emit(Result.Success(response))
    }.catch {
        emit(Result.Error(""))
    }
}