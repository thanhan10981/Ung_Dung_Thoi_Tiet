package com.example.ung_dung_thoi_tiet.utils

import com.example.ung_dung_thoi_tiet.R

fun weatherBackgroundRes(
    code: Int,
    isNight: Boolean
): Int {
    return when {
        code in 95..99 -> R.raw.thunderstorm
        code in 80..82 -> R.raw.rain
        code in 51..67 -> R.raw.rain
        code in 45..48 -> R.raw.fog
        code in 71..77 -> R.raw.snow
        code in 1..3   -> if (isNight) R.raw.cloudy else R.raw.cloud_sun
        code == 0      -> if (isNight) R.raw.moon else R.raw.sunicon
        else           -> R.raw.cloudy
    }
}
