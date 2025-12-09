package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SevenDayForecast() {
    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = "Dự báo 7 ngày",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp, start = 8.dp)
        )

        mock7DaysForecast.forEach { item ->
            DailyForecastItem(item)
        }
    }
}
