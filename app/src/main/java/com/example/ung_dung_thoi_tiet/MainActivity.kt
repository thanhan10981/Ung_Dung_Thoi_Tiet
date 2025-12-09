package com.example.ung_dung_thoi_tiet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.ung_dung_thoi_tiet.ui.home.WeatherScreen    // ← import quan trọng

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                WeatherScreen()   // ← gọi màn WeatherScreen
            }
        }
    }
}
