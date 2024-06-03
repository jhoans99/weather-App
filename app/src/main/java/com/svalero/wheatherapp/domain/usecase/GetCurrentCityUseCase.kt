package com.svalero.wheatherapp.domain.usecase

import com.svalero.wheatherapp.data.repository.LocationRepository
import javax.inject.Inject

class GetCurrentCityUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    fun getCurrentCity(): String =
        locationRepository.getCurrentCity()
}