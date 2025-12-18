package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ung_dung_thoi_tiet.model.HourlyForecast

@Composable
fun HourlyForecastSection(
    hourly: List<HourlyForecast>,
    onSeeMore: () -> Unit
) {
    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Dự báo 24 giờ tới",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Xem thêm",
                fontSize = 14.sp,
                color = Color(0xFF1976D2),
                modifier = Modifier.clickable { onSeeMore() }
            )
        }

        Spacer(Modifier.height(12.dp))

        HourlyForecastList(hourly)
    }
}
