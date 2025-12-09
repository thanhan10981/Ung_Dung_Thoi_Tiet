package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.background
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

@Composable
fun DailyForecastItem(item: DailyForecast) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(70.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // DAY LABEL
        Text(
            text = item.dayLabel,
            modifier = Modifier.weight(1.2f),
            fontSize = 16.sp,
            color = Color.Black
        )

        // ICON
        Icon(
            painter = painterResource(id = getDailyIconRes(item.iconName)),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(32.dp)
                .weight(0.7f)
        )

        // STATUS
        Text(
            text = item.status,
            modifier = Modifier.weight(1.8f),
            fontSize = 14.sp,
            color = Color.Gray
        )

        // HUMIDITY %
        Text(
            text = "${item.humidity}%",
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            color = Color(0xFF1976D2)
        )

        // LOW TEMP
        Text(
            text = "${item.tempLow}°",
            modifier = Modifier.weight(0.8f),
            fontSize = 14.sp,
            color = Color.Gray
        )

        // HIGH TEMP (bold)
        Text(
            text = "${item.tempHigh}°",
            modifier = Modifier.weight(0.8f),
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
