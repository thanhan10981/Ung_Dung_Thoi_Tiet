package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.Dp

data class StatModel(
    val title: String,
    val value: String,
    val subLabel: String,
    val icon: ImageVector,
    val color: Color,
    val showBar: Boolean = false
)

@Composable
fun WeatherStatsGrid() {

    val stats = listOf(
        StatModel("Cảm giác như", "30°C", "Nóng", Icons.Default.Thermostat, Color(0xFFFF6F00), showBar = true),
        StatModel("Độ ẩm", "65%", "Vừa phải", Icons.Default.WaterDrop, Color(0xFF2979FF), showBar = true),
        StatModel("Tốc độ gió", "15 km/h", "Đông Bắc", Icons.Default.Air, Color(0xFF00A86B)),
        StatModel("Tầm nhìn xa", "10 km", "Rất tốt", Icons.Default.Visibility, Color(0xFF9C27B0)),
        StatModel("Chỉ số UV", "5", "Trung bình", Icons.Default.WbSunny, Color(0xFFFFC107)),
        StatModel("Áp suất", "1013 hPa", "Ổn định", Icons.Default.Speed, Color(0xFF1565C0))
    )

    stats.chunked(2).forEach { rowItems ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            rowItems.forEach { item ->
                StatItem(
                    item = item,
                    modifier = Modifier.weight(1f)
                )
            }

            if (rowItems.size == 1) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        Spacer(Modifier.height(12.dp))
    }
}

@Composable
fun StatItem(
    item: StatModel,
    modifier: Modifier = Modifier,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color(0xFFEEEEEE),
    cornerRadius: Dp = 18.dp
) {
    Column(
        modifier = modifier
            .border(
                width = borderWidth,
                brush = SolidColor(borderColor),
                shape = RoundedCornerShape(cornerRadius)
            )
            .background(Color.White, RoundedCornerShape(cornerRadius))
            .padding(18.dp)
    ) {

        // 🔹 Title + Icon
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                item.icon,
                contentDescription = null,
                tint = item.color
            )
            Spacer(Modifier.width(8.dp))
            Text(item.title, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
        }

        Spacer(Modifier.height(12.dp))

        // 🔹 Giá trị chính
        Text(
            item.value,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(6.dp))

        // 🔹 Thanh trạng thái (chỉ hiện khi showBar = true)
        if (item.showBar) {
            Spacer(Modifier.height(6.dp))

            Box(
                modifier = Modifier
                    .height(6.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFE5E5E5), RoundedCornerShape(50))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.45f)
                        .background(item.color, RoundedCornerShape(50))
                )
            }
        }


        Spacer(Modifier.height(6.dp))

        // 🔹 Nhãn phụ
        Text(
            item.subLabel,
            color = item.color,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}