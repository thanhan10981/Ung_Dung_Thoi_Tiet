package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ung_dung_thoi_tiet.model.DetailItem
import com.example.ung_dung_thoi_tiet.model.WeatherResponse
import java.time.LocalDate

/* =========================
   PUBLIC COMPOSABLE
   ========================= */

@Composable
fun InfoDetailCard(
    weather: WeatherResponse
) {
    val borderColor = Color(0xFFD0D7F0)

    val daily = weather.daily
    val hourly = weather.hourly
    val current = weather.current_weather

    // 🌅 Sunrise / Sunset (API có)
    val sunrise = daily?.sunrise?.firstOrNull() ?: "--:--"
    val sunset = daily?.sunset?.firstOrNull() ?: "--:--"

    // 🌡 Dữ liệu cơ bản
    val temperature = current?.temperature ?: 0.0
    val humidity = hourly?.relativehumidity_2m?.firstOrNull() ?: 0
    val windSpeed = current?.windspeed ?: 0.0

    // 🔢 TÍNH TOÁN (API KHÔNG CÓ)
    val dewPoint = calculateDewPoint(temperature, humidity)
    val dayLength = calculateDayLength(sunrise, sunset)
    val windGust = calculateWindGust(windSpeed)
    val moonPhase = calculateMoonPhase()

    val items = listOf(
        DetailItem(
            label = "Mặt trời mọc",
            value = sunrise.takeLast(5),
            icon = Icons.Default.WbSunny,
            color = Color(0xFFFFC107)
        ),
        DetailItem(
            label = "Pha trăng",
            value = moonPhase,
            icon = Icons.Default.Brightness2,
            color = Color(0xFFAB47BC)
        ),
        DetailItem(
            label = "Mặt trời lặn",
            value = sunset.takeLast(5),
            icon = Icons.Default.DarkMode,
            color = Color(0xFF7E57C2)
        ),
        DetailItem(
            label = "Điểm sương",
            value = "$dewPoint°C",
            icon = Icons.Default.InvertColors,
            color = Color(0xFF29B6F6)
        ),
        DetailItem(
            label = "Giờ ban ngày",
            value = dayLength,
            icon = Icons.Default.AccessTime,
            color = Color(0xFF42A5F5)
        ),
        DetailItem(
            label = "Gió giật",
            value = "$windGust km/h",
            icon = Icons.Default.Air,
            color = Color(0xFF26A69A)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F8FF), RoundedCornerShape(20.dp))
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {

        Text(
            text = "Thông tin chi tiết hôm nay",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 300.dp)
        ) {
            items(items) { item ->
                InfoMiniItem(item)
            }
        }
    }
}

/* =========================
   MINI ITEM UI
   ========================= */



@Composable
fun InfoMiniItem(item: DetailItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(14.dp))
            .padding(12.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                item.icon,
                contentDescription = null,
                tint = item.color,
                modifier = Modifier.size(18.dp)
            )

            Spacer(Modifier.width(6.dp))

            Text(
                text = item.label,
                fontSize = 13.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(Modifier.height(6.dp))

        Text(
            text = item.value,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

/* =========================
   CALCULATION FUNCTIONS
   ========================= */

// 💧 Dew Point (công thức gần đúng – dùng phổ biến)
fun calculateDewPoint(temp: Double, humidity: Int): Int {
    return (temp - (100 - humidity) / 5.0).toInt()
}

// ⏱ Day length = sunset - sunrise
fun calculateDayLength(sunrise: String, sunset: String): String {
    return try {
        val start = sunrise.takeLast(5).split(":")
        val end = sunset.takeLast(5).split(":")

        val startMin = start[0].toInt() * 60 + start[1].toInt()
        val endMin = end[0].toInt() * 60 + end[1].toInt()

        val diff = endMin - startMin
        val hours = diff / 60
        val minutes = diff % 60

        "${hours}g ${minutes}p"
    } catch (e: Exception) {
        "--"
    }
}

// 🌬 Wind gust ước lượng
fun calculateWindGust(windSpeed: Double): Int {
    return (windSpeed * 1.4).toInt()
}

// 🌙 Moon phase (gần đúng – đủ dùng UI)
fun calculateMoonPhase(): String {
    val day = LocalDate.now().dayOfMonth
    return when {
        day <= 7 -> "Trăng non"
        day <= 14 -> "Trăng bán nguyệt"
        day <= 21 -> "Trăng tròn"
        else -> "Trăng khuyết"
    }
}
