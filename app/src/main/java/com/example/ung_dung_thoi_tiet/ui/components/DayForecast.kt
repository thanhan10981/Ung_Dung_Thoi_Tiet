package com.example.ung_dung_thoi_tiet.ui.components

import com.example.ung_dung_thoi_tiet.R

data class DailyForecast(
    val dayLabel: String,        // Hôm nay / Thứ 5 / Thứ 6 ...
    val iconName: String,        // sun, cloudy, rain...
    val status: String,          // Nắng ít mây, Dông bão...
    val humidity: Int,           // %
    val tempLow: Int,            // Nhiệt độ thấp
    val tempHigh: Int            // Nhiệt độ cao
)

fun getDailyIconRes(name: String): Int {
    return when (name) {
        "sun" -> R.drawable.sun
        "cloud" -> R.drawable.cloud
        "cloudy" -> R.drawable.cloudy
        "light" -> R.drawable.light
        "snowflake" -> R.drawable.snowflake
        "moon" -> R.drawable.moon
        "rain" -> R.drawable.snowflake
        "thunderstorm" -> R.drawable.thunderstorm
        else -> R.drawable.cloud
    }
}

val mock7DaysForecast = listOf(
    DailyForecast("Hôm nay", "sun", "Nắng ít mây", 10, 24, 32),
    DailyForecast("Thứ 5", "sun", "Nhiều mây", 25, 23, 30),
    DailyForecast("Thứ 6", "light", "Mưa rào", 70, 22, 28),
    DailyForecast("Thứ 7", "thunderstorm", "Dông bão", 85, 21, 26),
    DailyForecast("CN", "sun", "Nắng đẹp", 5, 24, 33),
    DailyForecast("Thứ 2", "sun", "Ít mây", 15, 25, 31),
    DailyForecast("Thứ 3", "cloudy", "U ám", 40, 23, 29),
)
