package com.example.ung_dung_thoi_tiet.model

data class AirQualityResponse(
    val hourly: AirHourly?
)

data class AirHourly(
    val pm2_5: List<Double>,
    val pm10: List<Double>,
    val ozone: List<Double>
)
