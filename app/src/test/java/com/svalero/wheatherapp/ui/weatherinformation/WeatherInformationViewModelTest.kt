package com.svalero.wheatherapp.ui.weatherinformation

import com.svalero.wheatherapp.coomons.Result
import com.svalero.wheatherapp.data.model.response.WeatherLocationResponse
import com.svalero.wheatherapp.domain.usecase.FetchNameCitiesUseCase
import com.svalero.wheatherapp.domain.usecase.FetchWeatherByCityUseCase
import com.svalero.wheatherapp.domain.usecase.GetCurrentCityUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WeatherInformationViewModelTest {

    @RelaxedMockK
    private lateinit var fetchNameCitiesUseCase: FetchNameCitiesUseCase
    @RelaxedMockK
    private lateinit var fetchWeatherByCityUseCase: FetchWeatherByCityUseCase
    @RelaxedMockK
    private lateinit var getCurrentCityUseCase: GetCurrentCityUseCase

    lateinit var viewModel: WeatherInformationViewModel
    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = WeatherInformationViewModel(
            fetchNameCitiesUseCase,
            fetchWeatherByCityUseCase,
            getCurrentCityUseCase
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should fetch weather for current city`(): Unit = runTest {
        // Given
        val currentCity = ""
        val mockWeatherLocation = WeatherLocationResponse(mockk(), mockk(), mockk())
        coEvery { getCurrentCityUseCase.getCurrentCity() } returns currentCity
        coEvery { fetchWeatherByCityUseCase.fetchWeatherByCity(currentCity) } returns flow {
            emit(Result.Success(mockWeatherLocation))
        }

        // When
        viewModel.initData()

        // Then
        val uiState = viewModel.uiState.value
        assertEquals(mockWeatherLocation, uiState.weatherLocation)
        assertEquals(currentCity, uiState.searchText)
        assertEquals(false, uiState.isLoading)
        assertEquals(false, uiState.isShowModalError)
    }

    @Test
    fun `fetchWeatherByCity should update UI state correctly`() = runTest {
        // Given
        val cityName = ""
        val mockWeatherLocation = WeatherLocationResponse(mockk(), mockk(), mockk())

        // When
        coEvery { fetchWeatherByCityUseCase.fetchWeatherByCity(cityName) } returns flow {
            emit(Result.Loading)
            emit(Result.Success(mockWeatherLocation))
        }

        viewModel.fetchWeatherByCity(cityName)

        // Then
        val updatedUiState = viewModel.uiState.value
        assertEquals(mockWeatherLocation, updatedUiState.weatherLocation)
        assertEquals(false, updatedUiState.isLoading)
        assertEquals("", updatedUiState.searchText)
        assertEquals(false, updatedUiState.isShowModalError)
    }

    @Test
    fun `fetchWeatherByCity should show modal when it is failed`() = runTest {
        // Given
        val cityName = ""

        // When
        coEvery { fetchWeatherByCityUseCase.fetchWeatherByCity(cityName) } returns flow {
            emit(Result.Loading)
            emit(Result.Error(""))
        }

        viewModel.fetchWeatherByCity(cityName)

        // Then
        val updatedUiState = viewModel.uiState.value
        assertEquals(false, updatedUiState.isLoading)
        assertEquals("", updatedUiState.searchText)
        assertEquals(true, updatedUiState.isShowModalError)
    }

    @Test
    fun `onSearchTextChange should update search and call fetchCitiesByName`() = runTest {
        // Given
        val searchText = "New York"
        coEvery { fetchNameCitiesUseCase.fetchCitiesByName(searchText) } returns flow {

        }

        // When
        viewModel.onSearchTextChange(searchText)

        // Then
        coVerify { fetchNameCitiesUseCase.fetchCitiesByName(searchText) }

        val uiState = viewModel.uiState.value
        assertEquals(searchText, uiState.searchText)
    }
}