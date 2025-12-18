package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ung_dung_thoi_tiet.model.HourlyForecast

@Composable
fun HourlyForecastList(
    hourly: List<HourlyForecast>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        hourly.forEach { hour ->
            HourlyForecastCard(hour)
        }
    }
}

