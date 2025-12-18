package com.example.ung_dung_thoi_tiet.utils

import com.example.ung_dung_thoi_tiet.R

fun getWeatherIconRes(code: Int): Int {
    return when (code) {

        // ☀️ Trời quang
        0 -> R.drawable.sun

        // 🌤 Ít mây (PHẢI có mặt trời)
        1 -> R.drawable.cloud_sun

        // ☁️ Có mây
        2 -> R.drawable.cloud

        // ☁️☁️ Nhiều mây / u ám
        3 -> R.drawable.cloudy

        // 🌫 Sương mù
        in 45..48 -> R.drawable.fog

        // 🌦 Mưa phùn
        in 51..57 -> R.drawable.drizzle

        // 🌧 Mưa
        in 61..67 -> R.drawable.rain

        // ❄️ Tuyết
        in 71..77 -> R.drawable.snowflake

        // 🌧🌧 Mưa rào
        in 80..82 -> R.drawable.rain_heavy

        // ⛈ Dông bão
        in 95..99 -> R.drawable.thunderstorm

        else -> R.drawable.cloudy
    }
}


