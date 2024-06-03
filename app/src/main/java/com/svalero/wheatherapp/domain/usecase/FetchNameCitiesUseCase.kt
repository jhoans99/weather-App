package com.svalero.wheatherapp.domain.usecase

import com.svalero.wheatherapp.coomons.Result
import com.svalero.wheatherapp.data.repository.FetchCitiesRepository
import com.svalero.wheatherapp.domain.model.NameCities
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchNameCitiesUseCase @Inject constructor(
    private val fetchCitiesRepository: FetchCitiesRepository
) {

    suspend fun fetchCitiesByName(name: String): Flow<Result<ArrayList<NameCities>>> {
        return fetchCitiesRepository.fetchCities(name)
    }
}