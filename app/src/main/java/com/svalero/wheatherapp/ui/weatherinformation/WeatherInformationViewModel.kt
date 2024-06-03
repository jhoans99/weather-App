package com.svalero.wheatherapp.ui.weatherinformation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.svalero.wheatherapp.coomons.Result
import com.svalero.wheatherapp.domain.usecase.FetchNameCitiesUseCase
import com.svalero.wheatherapp.domain.usecase.FetchWeatherByCityUseCase
import com.svalero.wheatherapp.domain.usecase.GetCurrentCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherInformationViewModel @Inject constructor(
    private val fetchNameCitiesUseCase: FetchNameCitiesUseCase,
    private val fetchWeatherByCityUseCase: FetchWeatherByCityUseCase,
    private val getCurrentCityUseCase: GetCurrentCityUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(WeatherInformationUiState())
    val uiState: StateFlow<WeatherInformationUiState> = _uiState


    fun initData() {
        fetchWeatherByCity(getCurrentCityUseCase.getCurrentCity())
    }

    fun fetchCity(value: String) {
        viewModelScope.launch {
            fetchNameCitiesUseCase.fetchCitiesByName(value).collect {
                when(it) {
                    is Result.Error -> {}
                    Result.Loading -> {}
                    is Result.Success -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                listCities = it.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun fetchWeatherByCity(nameCity: String) {
        viewModelScope.launch {
            fetchWeatherByCityUseCase.fetchWeatherByCity(nameCity).collect {
                when(it) {
                    is Result.Error -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                isShowModalError = true
                            )
                        }
                    }
                    Result.Loading -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                listCities = ArrayList(),
                                isLoading = true,
                                searchText = nameCity
                            )
                        }
                    }
                    is Result.Success -> {
                        _uiState.update { uiState ->
                            uiState.copy(
                                isLoading = false,
                                weatherLocation = it.data,
                                searchText = ""
                                )
                        }
                    }
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        fetchCity(text)
        _uiState.update { uiState ->
            uiState.copy(searchText = text)
        }
    }

    fun onDismissModal() {
        _uiState.update { uiState ->
            uiState.copy(isShowModalError = false)
        }
    }

}