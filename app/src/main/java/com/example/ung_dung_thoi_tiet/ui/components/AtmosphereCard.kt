package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 🎯 Hàm xác định trạng thái + màu theo chuẩn AQI quốc tế
fun getAQIStatus(aqi: Int): Triple<String, Color, Color> {
    return when (aqi) {
        in 0..50 -> Triple("Tốt", Color(0xFF2E7D32), Color(0xFFD4F7D4))
        in 51..100 -> Triple("Trung bình", Color(0xFFF9A825), Color(0xFFFFF4C2))
        in 101..150 -> Triple("Kém (nhạy cảm)", Color(0xFFEF6C00), Color(0xFFFFE0B2))
        in 151..200 -> Triple("Kém", Color(0xFFC62828), Color(0xFFFFCDD2))
        in 201..300 -> Triple("Rất kém", Color(0xFF6A1B9A), Color(0xFFE1BEE7))
        else -> Triple("Nguy hại", Color(0xFF4E342E), Color(0xFFD7CCC8))
    }
}

@Composable
fun AtmosphereCard(aqi: Int = 60) {

    // Lấy trạng thái từ bảng chuẩn
    val (status, textColor, bgColor) = getAQIStatus(aqi)

    // Cho progress chạy theo AQI (tối đa 500)
    val progress = (aqi.toFloat() / 500f).coerceIn(0f, 1f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(20.dp))
            .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(20.dp))  // ⭐ border
            .padding(20.dp)
    ) {

        Text("Chỉ số chất lượng không khí", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(12.dp))

        // Chỉ số + trạng thái
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(aqi.toString(), fontSize = 40.sp, fontWeight = FontWeight.Bold, color = textColor)

            Box(
                Modifier
                    .background(bgColor, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(status, color = textColor, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(Modifier.height(14.dp))

        // ⭐ Thanh progress chạy đúng theo AQI
        Box(
            Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(Color(0xFFE0E0E0), RoundedCornerShape(50))
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                    .background(textColor, RoundedCornerShape(50))
            )
        }

        Spacer(Modifier.height(16.dp))

        // 3 chỉ số phụ
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AQISub("PM2.5", "15 µg/m³")
            AQISub("PM10", "25 µg/m³")
            AQISub("O³", "62 µg/m³")
        }
    }
}

@Composable
fun AQISub(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, fontSize = 14.sp, color = Color.Gray)
        Text(value, fontWeight = FontWeight.Bold)
    }
}
