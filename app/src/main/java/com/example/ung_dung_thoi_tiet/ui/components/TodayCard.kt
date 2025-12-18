package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ung_dung_thoi_tiet.utils.currentTime
import com.example.ung_dung_thoi_tiet.utils.weatherAnimationRes
import com.example.ung_dung_thoi_tiet.utils.weatherText

@Composable
fun TodayCard(
    city: String,
    temperature: Int,
    weatherCode: Int,
    highTemp: Int,
    lowTemp: Int,
    isNight: Boolean
) {

    // 🌫 Glass background theo ngày / đêm
    val glassBackground = if (isNight) {
        Color(0xFF1C1C1E).copy(alpha = 0.75f)   // 🌙 đêm
    } else {
        Color(0xFFF5F5F5).copy(alpha = 0.85f)   // ☀️ ngày
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = glassBackground,
                shape = RoundedCornerShape(22.dp)
            )
            .padding(20.dp)
    ) {

        /* ===== LOCATION ===== */
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color.Gray
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = city,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        /* ===== TIME ===== */
        Text(
            text = "Cập nhật: ${currentTime()}",
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        /* ===== WEATHER ANIMATION ===== */
        WeatherAnimation(
            rawRes = weatherAnimationRes(weatherCode, isNight),
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(12.dp))

        /* ===== TEMPERATURE ===== */
        Text(
            text = "$temperature°",
            fontSize = 54.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        /* ===== DESCRIPTION ===== */
        Text(
            text = weatherText(weatherCode),
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        /* ===== HIGH / LOW ===== */
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Cao: $highTemp°", fontSize = 16.sp)
            Spacer(Modifier.width(24.dp))
            Text("Thấp: $lowTemp°", fontSize = 16.sp)
        }
    }
}
