package com.svalero.wheatherapp.domain.usecase

import com.svalero.wheatherapp.coomons.Result
import com.svalero.wheatherapp.data.model.response.WeatherLocationResponse
import com.svalero.wheatherapp.data.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchWeatherByCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend fun fetchWeatherByCity(nameCity: String): Flow<Result<WeatherLocationResponse>> {
        return weatherRepository.fetchWeatherByCity(nameCity)
    }
}