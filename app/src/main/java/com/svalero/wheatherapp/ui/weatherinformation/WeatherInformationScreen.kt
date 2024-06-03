@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.svalero.wheatherapp.ui.weatherinformation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.svalero.wheatherapp.R
import com.svalero.wheatherapp.ui.components.AlertDialogComponent
import com.svalero.wheatherapp.ui.components.CityListItem
import com.svalero.wheatherapp.ui.components.ItemForecastList
import com.svalero.wheatherapp.ui.components.ProgressBar
import com.svalero.wheatherapp.ui.theme.Orange
import com.svalero.wheatherapp.ui.theme.titleBold

@Composable
fun WeatherInformationScreen(
    weatherInformationViewModel: WeatherInformationViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        weatherInformationViewModel.initData()
    }
    val uiState by weatherInformationViewModel.uiState.collectAsState()
    when {
        uiState.isLoading -> ProgressBar()
        uiState.isShowModalError -> AlertDialogComponent(message = stringResource(id = R.string.message_error)
        ) {
            weatherInformationViewModel.onDismissModal()
        }
    }
    WeatherInformation(uiState)
}

@Composable
fun WeatherInformation(
    uiState: WeatherInformationUiState,
    weatherInformationViewModel: WeatherInformationViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                        Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    Icon(
                        Icons.Filled.Refresh,
                        null,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clickable {
                                weatherInformationViewModel.fetchWeatherByCity(
                                    uiState.weatherLocation?.location?.name ?: ""
                                )
                            }
                    )
                })
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            val (searchView,weatherContent) = createRefs()
            SearchView(
                modifier = Modifier.constrainAs(searchView) {
                  top.linkTo(parent.top)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
                },
                weatherInformationViewModel::onSearchTextChange,
                uiState,
                weatherInformationViewModel::fetchWeatherByCity
            )
            WeatherContent(
                modifier = Modifier.constrainAs(weatherContent){
                    top.linkTo(searchView.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                uiState
            )
        }
    }
}

@Composable
fun WeatherContent(modifier: Modifier, uiState: WeatherInformationUiState) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize()
    ) {
        val (textNameCity,textCurrentTemperature,iconWeather) = createRefs()
        val (textConditionWeather,listForecastWeather,textAvgTemperature) = createRefs()
        val labelForecast = createRef()
        Text(
            text = uiState.weatherLocation?.location?.name ?: "",
            modifier = Modifier
                .padding(top = 20.dp)
                .constrainAs(textNameCity) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            style = titleBold,
            fontSize = 30.sp,
            color = Orange
        )
        AsyncImage(
            model = "https:${uiState.weatherLocation?.currentWeather?.condition?.icon}",
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(iconWeather) {
                    top.linkTo(textNameCity.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = "${uiState.weatherLocation?.currentWeather?.temperatureCentigrade ?: "-"}°C",
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(textCurrentTemperature) {
                    top.linkTo(iconWeather.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            style = titleBold,
            fontSize = 25.sp
        )
        Text(
            text = uiState.weatherLocation?.currentWeather?.condition?.text ?:  "",
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(textConditionWeather) {
                    top.linkTo(textCurrentTemperature.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            style = titleBold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Temp: ${uiState.weatherLocation?.forecast?.forecastDay?.get(0)?.day?.avgTemperature ?: ""}°C",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(5.dp)
                .constrainAs(textAvgTemperature) {
                    top.linkTo(textConditionWeather.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(id = R.string.label_forecast_three_days),
            style = titleBold,
            fontSize = 22.sp,
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(labelForecast) {
                    top.linkTo(textAvgTemperature.bottom)
                    start.linkTo(parent.start)
                }
        )
        ForecastWeather(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .constrainAs(listForecastWeather) {
                    top.linkTo(labelForecast.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            uiState
        )
    }
}

@Composable
fun ForecastWeather(
    modifier: Modifier,
    uiState: WeatherInformationUiState
) {
    val calculatedHeight = (uiState.weatherLocation?.forecast?.forecastDay?.size ?: 0) * 80
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(calculatedHeight.dp)
    ) {
        items(uiState.weatherLocation?.forecast?.forecastDay ?: ArrayList()) {
            ItemForecastList(modifier = Modifier, it)
        }
    }
}

@Composable
fun SearchView(
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    uiState: WeatherInformationUiState,
    onItemSelected: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(modifier = modifier) {
        TextField(
            value = uiState.searchText,
            onValueChange = {
                onValueChange(it)
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(10.dp)),
            placeholder = {
                Text(text = stringResource(id = R.string.hint_search_by_city))
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            }
        )
        when {
            uiState.listCities.isNotEmpty() -> {
                val calculatedHeight = (uiState.listCities.size) * 20
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .background(Color.LightGray)
                        .height(calculatedHeight.dp)
                ) {
                    items(uiState.listCities) {
                        CityListItem(it) {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            onItemSelected(it)
                        }
                    }
                }
            }
        }
    }
}
