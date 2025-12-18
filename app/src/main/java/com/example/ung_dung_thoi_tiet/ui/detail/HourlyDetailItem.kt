package com.example.ung_dung_thoi_tiet.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ung_dung_thoi_tiet.model.HourlyForecast

import com.example.ung_dung_thoi_tiet.utils.getWeatherIconRes
import com.example.ung_dung_thoi_tiet.utils.weatherText

@Composable
fun HourlyDetailItem(item: HourlyForecast) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            item.time,
            modifier = Modifier.width(50.dp),
            fontSize = 14.sp
        )

        Icon(
            painter = painterResource(getWeatherIconRes(item.weatherCode)),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(28.dp)
        )

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text("${item.temperature}°C", fontSize = 16.sp)
            Text(
                weatherText(item.weatherCode),
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text("Độ ẩm ${item.humidity}%", fontSize = 12.sp, color = Color.Gray)
        }

        Text(
            "${item.windSpeed} km/h",
            fontSize = 13.sp,
            color = Color(0xFF1976D2)
        )
    }
}
