package com.svalero.wheatherapp.data.repository

import com.svalero.wheatherapp.data.datasource.remote.LocationDataSource
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationDataSource: LocationDataSource
) {

    fun getCurrentCity(): String =
        locationDataSource.getCurrentCity()
}