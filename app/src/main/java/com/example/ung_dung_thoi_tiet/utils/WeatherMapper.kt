package com.example.ung_dung_thoi_tiet.utils

import com.example.ung_dung_thoi_tiet.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// 📝 Text mô tả thời tiết (chuẩn Open-Meteo)
fun weatherText(code: Int): String = when (code) {
    0 -> "Trời quang"
    1 -> "Ít mây"
    2 -> "Có mây"
    3 -> "Nhiều mây"
    in 45..48 -> "Sương mù"
    in 51..57 -> "Mưa phùn"
    in 61..67 -> "Mưa"
    in 71..77 -> "Tuyết"
    in 80..82 -> "Mưa rào"
    in 95..99 -> "Dông bão"
    else -> "Thời tiết"
}

// 🎞 Animation thời tiết (ngày / đêm đúng logic)
fun weatherAnimationRes(code: Int, isDay: Boolean): Int {
    return when {

        // ⛈ Dông bão
        code in 95..99 -> R.raw.thunder

        // 🌧 Mưa rào
        code in 80..82 -> R.raw.rain

        // 🌧 Mưa thường
        code in 61..67 -> R.raw.rain

        // 🌦 Mưa phùn
        code in 51..57 -> R.raw.drizzle

        // ❄️ Tuyết
        code in 71..77 -> R.raw.snow

        // 🌫 Sương mù
        code in 45..48 -> R.raw.fog

        // 🌤 Ít mây
        code == 1 -> if (isDay) R.raw.cloud_sun else R.raw.moon

        // ☁️ Có mây
        code == 2 -> R.raw.cloudy

        // ☁️☁️ Nhiều mây
        code == 3 -> R.raw.cloudy

        // ☀️ / 🌙 Trời quang
        code == 0 -> if (isDay) R.raw.sunicon else R.raw.moon

        else -> R.raw.cloudy
    }
}

// ⏰ Giờ hiện tại
fun currentTime(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return LocalDateTime.now().format(formatter)
}
