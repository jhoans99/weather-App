package com.svalero.wheatherapp.data.repository

import com.svalero.wheatherapp.coomons.Result
import com.svalero.wheatherapp.data.datasource.remote.WeatherDataSource
import com.svalero.wheatherapp.domain.model.NameCities
import com.svalero.wheatherapp.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchCitiesRepository @Inject constructor(
    private val weatherDataSource: WeatherDataSource
) {

    suspend fun fetchCities(name: String): Flow<Result<ArrayList<NameCities>>> = flow<Result<ArrayList<NameCities>>> {
        emit(Result.Loading)
        val listCitiesResponse = weatherDataSource.fetchCities(name).map {
            it.toDomain()
        }
        emit(Result.Success(listCitiesResponse as ArrayList))
    }.catch {
        emit(Result.Error(it.message ?: "Ha ocurrido un error inesperado"))
    }

}