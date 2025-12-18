package com.example.ung_dung_thoi_tiet.ui.home

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ung_dung_thoi_tiet.data.repository.WeatherRepository
import com.example.ung_dung_thoi_tiet.model.AirQualityResponse
import com.example.ung_dung_thoi_tiet.model.WeatherResponse
import com.example.ung_dung_thoi_tiet.utils.LocationUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()

    // ================= STATE =================
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _airQuality = MutableStateFlow<AirQualityResponse?>(null)
    val airQuality: StateFlow<AirQualityResponse?> = _airQuality

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    var searchQuery by mutableStateOf("")
        private set

    var cityName by mutableStateOf("Quy Nhơn")
        private set

    // ================= ACTION =================

    fun onQueryChange(text: String) {
        searchQuery = text
    }

    fun clearSearch() {
        searchQuery = ""
    }

    fun loadDefaultCity() {
        cityName = "Quy Nhơn"
        loadWeather(13.7695, 109.2237)
    }

    fun refresh(context: Context) {
        searchWeather(context)
    }

    /** 🔍 Search theo tên thành phố */
    fun searchWeather(context: Context) {
        if (searchQuery.isBlank()) {
            loadByCurrentLocation(context)
            return
        }

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val city = repository.searchCity(searchQuery)
                if (city != null) {
                    cityName = city.name
                    loadWeather(city.latitude, city.longitude)
                    clearSearch()
                } else {
                    _error.value = "Không tìm thấy thành phố"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    /** 🌍 Load theo lat / lon */
    fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                _weather.value = repository.getWeather(lat, lon)
                _airQuality.value = repository.getAirQuality(lat, lon)

            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    /** 📍 GPS */
    private fun loadByCurrentLocation(context: Context) {
        _isLoading.value = true

        LocationUtils.getCurrentLocation(context) { lat, lon ->
            cityName = "Vị trí hiện tại"
            loadWeather(lat, lon)
        }
    }
}
