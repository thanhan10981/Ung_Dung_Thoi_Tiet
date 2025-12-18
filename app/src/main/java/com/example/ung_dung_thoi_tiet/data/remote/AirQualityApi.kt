package com.example.ung_dung_thoi_tiet.data.remote

import com.example.ung_dung_thoi_tiet.model.AirQualityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityApi {

    @GET("v1/air-quality")
    suspend fun getAirQuality(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("hourly") hourly: String = "pm2_5,pm10,ozone"
    ): AirQualityResponse
}
