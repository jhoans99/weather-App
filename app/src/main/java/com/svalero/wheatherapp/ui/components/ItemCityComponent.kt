package com.svalero.wheatherapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.svalero.wheatherapp.domain.model.NameCities

@Composable
fun CityListItem(
    cityItem: NameCities,
    modifier: Modifier = Modifier,
    citySelected: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                citySelected(cityItem.nameCity)
            }
            .padding(10.dp)
    ) {
        Text(
            text ="${cityItem.nameCity},${cityItem.nameCountry}"
        )
    }
}