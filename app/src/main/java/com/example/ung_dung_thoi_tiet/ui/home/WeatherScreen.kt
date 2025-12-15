package com.example.ung_dung_thoi_tiet.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ung_dung_thoi_tiet.ui.components.*

@Composable
fun WeatherScreen() {


    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(WindowInsets.statusBars.asPaddingValues())
                    .padding(16.dp)
            ) {
                WeatherHeader()
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {

            Spacer(Modifier.height(16.dp))

            TodayCard()

            Spacer(Modifier.height(20.dp))

            WeatherStatsGrid()

            Spacer(Modifier.height(20.dp))

            InfoDetailCard()

            Spacer(Modifier.height(20.dp))

            AtmosphereCard()

            Spacer(Modifier.height(20.dp))

            HourlyForecastList()

            Spacer(Modifier.height(20.dp))

            SevenDayForecast()

            Spacer(Modifier.height(20.dp))
        }
    }
}
