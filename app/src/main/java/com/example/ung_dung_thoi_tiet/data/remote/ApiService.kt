package com.example.ung_dung_thoi_tiet.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private const val BASE_URL = "https://api.open-meteo.com/"

    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    private const val GEO_URL = "https://geocoding-api.open-meteo.com/"

    val geoApi: GeocodingApi by lazy {
        Retrofit.Builder()
            .baseUrl(GEO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeocodingApi::class.java)
    }
    private const val AIR_QUALITY_URL = "https://air-quality-api.open-meteo.com/"

    val airApi: AirQualityApi by lazy {
        Retrofit.Builder()
            .baseUrl(AIR_QUALITY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AirQualityApi::class.java)
    }

}