package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ung_dung_thoi_tiet.model.StatModel
import com.example.ung_dung_thoi_tiet.model.WeatherResponse

/* =========================
   GRID – SOFT GLASS CONTAINER
   ========================= */
@Composable
fun WeatherStatsGrid(
    weather: WeatherResponse
) {
    val current = weather.current_weather
    val hourly = weather.hourly

    val humidity = hourly?.relativehumidity_2m?.firstOrNull() ?: 0
    val windSpeed = current?.windspeed ?: 0.0
    val feelsLike = current?.temperature?.toInt() ?: 0

    data class FeelInfo(
        val label: String,
        val color: Color,
        val progress: Float
    )

    fun feelsLikeInfo(temp: Int): FeelInfo = when {
        temp < 15 -> FeelInfo("Rất lạnh", Color(0xFF42A5F5), temp / 45f)
        temp < 22 -> FeelInfo("Lạnh", Color(0xFF64B5F6), temp / 45f)
        temp < 26 -> FeelInfo("Dễ chịu", Color(0xFF66BB6A), temp / 45f)
        temp < 32 -> FeelInfo("Nóng", Color(0xFFFFA726), temp / 45f)
        else -> FeelInfo("Rất nóng", Color(0xFFEF5350), temp / 45f)
    }

    val feels = feelsLikeInfo(feelsLike)

    val stats = listOf(
        StatModel("Cảm giác như", "$feelsLike°C", feels.label, Icons.Default.Thermostat, feels.color, true, feels.progress),
        StatModel("Độ ẩm", "$humidity%", "Vừa phải", Icons.Default.WaterDrop, Color(0xFF2979FF), true, humidity / 100f),
        StatModel("Tốc độ gió", "${windSpeed.toInt()} km/h", "Gió nhẹ", Icons.Default.Air, Color(0xFF00A86B)),
        StatModel("Tầm nhìn", "10 km", "Rất tốt", Icons.Default.Visibility, Color(0xFF9C27B0)),
        StatModel("UV", "5", "Trung bình", Icons.Default.WbSunny, Color(0xFFFFC107)),
        StatModel("Áp suất", "1013 hPa", "Ổn định", Icons.Default.Speed, Color(0xFF1565C0))
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White.copy(alpha = 0.10f), // 🌫 nền rất nhẹ
                RoundedCornerShape(22.dp)
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.18f),
                RoundedCornerShape(22.dp)
            )
            .padding(12.dp)
    ) {
        stats.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                row.forEach {
                    SoftGlassStatItem(it, Modifier.weight(1f))
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}

/* =========================
   STAT ITEM – SOFT GLASS
   ========================= */
@Composable
fun SoftGlassStatItem(
    item: StatModel,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 18.dp
) {
    Column(
        modifier = modifier
            .background(
                Color.White.copy(alpha = 0.22f), // 🌫 glass mỏng
                RoundedCornerShape(cornerRadius)
            )
            .border(
                1.dp,
                Color.White.copy(alpha = 0.30f),
                RoundedCornerShape(cornerRadius)
            )
            .padding(14.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = item.color
            )
            Spacer(Modifier.width(8.dp))
            Text(
                item.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            item.value,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )

        if (item.showBar) {
            Spacer(Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .fillMaxWidth()
                    .background(
                        Color.Black.copy(alpha = 0.10f),
                        RoundedCornerShape(50)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(item.progress.coerceIn(0f, 1f))
                        .background(item.color, RoundedCornerShape(50))
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        Text(
            item.subLabel,
            color = item.color,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
