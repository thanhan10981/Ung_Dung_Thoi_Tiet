package com.example.ung_dung_thoi_tiet.ui.components


import com.example.ung_dung_thoi_tiet.R

data class HourlyForecast(
    val time: String,
    val temperature: Int,
    val humidity: Int,
    val windSpeed: Double,
    val iconName: String
)

fun getWeatherIconRes(name: String): Int {
    return when (name) {
        "sun" -> R.drawable.sun
        "cloud" -> R.drawable.cloud
        "cloudy" -> R.drawable.cloudy
        "light" -> R.drawable.light
        "snowflake" -> R.drawable.snowflake
        "moon" -> R.drawable.moon
        "thunderstorm" -> R.drawable.thunderstorm
        "wind" -> R.drawable.wind
        else -> R.drawable.cloud
    }
}

val mockHourlyForecast = listOf(
    HourlyForecast("01:00", 24, 84, 3.2, "cloudy"),
    HourlyForecast("02:00", 23, 86, 2.8, "light"),   // SỬA "rain" → "light"
    HourlyForecast("03:00", 23, 88, 3.5, "cloud"),
    HourlyForecast("04:00", 22, 90, 3.0, "thunderstorm"),
    HourlyForecast("05:00", 22, 87, 2.6, "light"),
    HourlyForecast("06:00", 23, 80, 3.1, "sun")
)
