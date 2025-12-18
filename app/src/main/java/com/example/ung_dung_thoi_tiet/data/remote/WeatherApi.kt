package com.example.ung_dung_thoi_tiet.data.remote

import com.example.ung_dung_thoi_tiet.model.GeocodingResponse
import com.example.ung_dung_thoi_tiet.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true,
        @Query("hourly")
        hourly: String =
            "temperature_2m,relativehumidity_2m,wind_speed_10m,weathercode",
        @Query("daily")
        daily: String =
            "weathercode,temperature_2m_max,temperature_2m_min,sunrise,sunset",

        @Query("timezone") timezone: String = "Asia/Ho_Chi_Minh"
    ): WeatherResponse

}
interface GeocodingApi {

    @GET("v1/search")
    suspend fun searchCity(
        @Query("name") name: String,
        @Query("count") count: Int = 1,
        @Query("language") language: String = "vi"
    ): GeocodingResponse
}
