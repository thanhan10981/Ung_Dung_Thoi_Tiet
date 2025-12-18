package com.example.ung_dung_thoi_tiet.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class WeatherResponse(
    val current_weather: CurrentWeather?,
    val hourly: Hourly?,
    val daily: Daily?
)

data class CurrentWeather(
    val temperature: Double,
    val windspeed: Double,
    val weathercode: Int
)

data class Hourly(
    val time: List<String>,
    val temperature_2m: List<Double>,
    val relativehumidity_2m: List<Int>,
    val wind_speed_10m: List<Double>,
    val weathercode: List<Int>
)


data class Daily(
    val time: List<String>,
    val weathercode: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val sunrise: List<String>,
    val sunset: List<String>
)

data class GeocodingResponse(
    val results: List<GeoCity>?
)

data class GeoCity(
    val name: String,
    val latitude: Double,
    val longitude: Double
)
data class StatModel(
    val title: String,
    val value: String,
    val subLabel: String,
    val icon: ImageVector,
    val color: Color,
    val showBar: Boolean = false,
    val progress: Float = 0f
)
data class DetailItem(
    val label: String,
    val value: String,
    val icon: ImageVector,
    val color: Color
)
data class DailyForecast(
    val dayLabel: String,
    val weatherCode: Int,
    val status: String,
    val tempLow: Int,
    val tempHigh: Int
)
data class HourlyForecast(
    val time: String,
    val temperature: Int,
    val humidity: Int,
    val windSpeed: Int,
    val weatherCode: Int
)