package com.svalero.wheatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.svalero.wheatherapp.R
import com.svalero.wheatherapp.coomons.utils.DateUtils.getNameByEpoch
import com.svalero.wheatherapp.data.model.ForecastDayDataDTO

@Composable
fun ItemForecastList(
    modifier: Modifier,
    forecastDayDataDTO: ForecastDayDataDTO
) {
    ConstraintLayout(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        val (iconWeather, textDay, textTemperatureDay) = createRefs()
        val (textConditionWeather) = createRefs()
        AsyncImage(
            model = "https:${forecastDayDataDTO.day.conditionDTO.icon}",
            contentDescription = null,
            modifier = Modifier.constrainAs(iconWeather) {
                top.linkTo(parent.top)
                start.linkTo(parent.start,5.dp)
            }
        )
        Text(
            text = getNameByEpoch(forecastDayDataDTO.date),
            modifier = Modifier.constrainAs(textDay) {
                top.linkTo(parent.top)
                start.linkTo(iconWeather.end)
                end.linkTo(textTemperatureDay.start)
                bottom.linkTo(parent.bottom)
            },
            fontSize = 16.sp
        )
        Text(
            text = "Mín.: ${forecastDayDataDTO.day.minTemperature}°C - Máx.: ${forecastDayDataDTO.day.maxTemperature}°C",
            modifier = Modifier.constrainAs(textTemperatureDay) {
                top.linkTo(textConditionWeather.bottom)
                end.linkTo(parent.end)
            },
            fontSize = 10.sp
        )
        Text(
            text = forecastDayDataDTO.day.conditionDTO.text,
            modifier = Modifier.constrainAs(textConditionWeather) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            },
            fontSize = 10.sp
        )
    }
}