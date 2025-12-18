package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ung_dung_thoi_tiet.R
import com.example.ung_dung_thoi_tiet.model.HourlyForecast
import com.example.ung_dung_thoi_tiet.utils.getWeatherIconRes

@Composable
fun HourlyForecastCard(item: HourlyForecast) {

    Card(
        modifier = Modifier
            .width(85.dp)
            .height(180.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp)),   // ⭐ BORDER NÈ
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)   // ⭐ nền trắng
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = item.time, fontSize = 14.sp, color = Color.Black)

            Spacer(Modifier.height(10.dp))

            Icon(
                painter = painterResource(id = getWeatherIconRes(item.weatherCode)),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )

            Spacer(Modifier.height(12.dp))

            Text("${item.temperature}°C", color = Color.Black, fontSize = 16.sp)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.humidity),   // ⭐ icon thật
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(14.dp)
                )

                Spacer(Modifier.width(4.dp))

                Text("${item.humidity}%", color = Color(0xFF1976D2), fontSize = 12.sp)
            }


            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.wind),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(14.dp)
                )

                Spacer(Modifier.width(4.dp))

                Text("${item.windSpeed} km/h", color = Color.Gray, fontSize = 12.sp)
            }

        }
    }
}
