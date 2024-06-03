package com.svalero.wheatherapp.ui.weatherinformation

import com.svalero.wheatherapp.data.model.response.WeatherLocationResponse
import com.svalero.wheatherapp.domain.model.NameCities

data class WeatherInformationUiState(
    var isLoading: Boolean = false,
    var listCities: ArrayList<NameCities> = ArrayList(),
    var isSearching: Boolean = false,
    var searchText: String = "",
    var weatherLocation: WeatherLocationResponse? = null,
    var isShowModalError: Boolean = false
)
