package com.example.ung_dung_thoi_tiet.data.repository

import com.example.ung_dung_thoi_tiet.data.remote.ApiService
import com.example.ung_dung_thoi_tiet.model.GeoCity
import com.example.ung_dung_thoi_tiet.model.WeatherResponse

class WeatherRepository {

    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse {
        return ApiService.api.getWeather(lat, lon)
    }
    suspend fun searchCity(name: String): GeoCity? {
        return ApiService.geoApi
            .searchCity(name)
            .results
            ?.firstOrNull()
    }
    suspend fun getAirQuality(lat: Double, lon: Double) =
        ApiService.airApi.getAirQuality(lat, lon)

}
