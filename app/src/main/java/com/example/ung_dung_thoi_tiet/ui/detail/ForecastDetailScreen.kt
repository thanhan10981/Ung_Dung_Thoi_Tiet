package com.example.ung_dung_thoi_tiet.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ung_dung_thoi_tiet.model.HourlyForecast
import com.example.ung_dung_thoi_tiet.ui.components.*
import com.example.ung_dung_thoi_tiet.ui.home.WeatherViewModel
import com.example.ung_dung_thoi_tiet.utils.weatherBackgroundRes
import java.time.LocalDateTime

@Composable
fun ForecastDetailScreen(
    onBack: () -> Unit,
    viewModel: WeatherViewModel
) {
    val weatherState by viewModel.weather.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }

    if (weatherState == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Đang tải dữ liệu...")
        }
        return
    }

    val weather = weatherState!!

    /* ===== BUILD 48H DATA ===== */
    val hourlyData = weather.hourly?.let { hourly ->
        val currentHour = LocalDateTime.now().hour
        val startIndex = hourly.time.indexOfFirst {
            it.contains(String.format("%02d", currentHour))
        }.coerceAtLeast(0)

        (0 until 48).mapNotNull { offset ->
            val index = startIndex + offset
            if (index < hourly.time.size) {
                HourlyForecast(
                    time = hourly.time[index].takeLast(5),
                    temperature = hourly.temperature_2m[index].toInt(),
                    humidity = hourly.relativehumidity_2m[index],
                    windSpeed = hourly.wind_speed_10m[index].toInt(),
                    weatherCode = hourly.weathercode.getOrNull(index) ?: 0
                )
            } else null
        }
    } ?: emptyList()

    val displayData =
        if (selectedTab == 0) hourlyData.take(24) else hourlyData.take(48)

    /* =========================
       🌈 BACKGROUND + CONTENT
       ========================= */
    Box(modifier = Modifier.fillMaxSize()) {

        // 🌈 BACKGROUND ANIMATION
        val hour = LocalDateTime.now().hour
        val isNight = hour < 6 || hour >= 18

        WeatherAnimatedBackground(
            rawRes = weatherBackgroundRes(
                weather.current_weather?.weathercode ?: 0,
                isNight
            )
        )

        // 🧊 CONTENT
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            ForecastDetailHeader(
                city = viewModel.cityName,
                onBack = onBack
            )

            Spacer(Modifier.height(16.dp))

            ForecastTabs(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            Spacer(Modifier.height(20.dp))

            // 🌡 Chart (glass card)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White.copy(alpha = 0.85f),
                        androidx.compose.foundation.shape.RoundedCornerShape(20.dp)
                    )
                    .padding(12.dp)
            ) {
                TemperatureChart(displayData)
            }

            Spacer(Modifier.height(20.dp))

            // 🕒 Hourly list
            displayData.forEach {
                HourlyDetailItem(it)
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}
