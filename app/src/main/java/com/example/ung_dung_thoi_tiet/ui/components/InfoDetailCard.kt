package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Brightness2
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.InvertColors
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoDetailCard() {

    val borderColor = Color(0xFFD0D7F0)  // viền đậm hơn chút

    val items = listOf(
        DetailItem("Mặt trời mọc", "05:42", Icons.Default.WbSunny, Color(0xFFFFC107)),
        DetailItem("Mặt trời lặn", "18:25", Icons.Default.DarkMode, Color(0xFF7E57C2)),
        DetailItem("Giờ ban ngày", "12g\n43p", Icons.Default.AccessTime, Color(0xFF42A5F5)),
        DetailItem("Pha trăng", "Trăng\ntròn", Icons.Default.Brightness2, Color(0xFFAB47BC)),
        DetailItem("Điểm sương", "22°C", Icons.Default.InvertColors, Color(0xFF29B6F6)),
        DetailItem("Gió giật", "22 km/h", Icons.Default.Air, Color(0xFF26A69A))
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F8FF), RoundedCornerShape(20.dp))
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Text(
            "Thông tin chi tiết hôm nay",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        // CHIA 2 CỘT
        Row(modifier = Modifier.fillMaxWidth()) {

            Column(modifier = Modifier.weight(1f)) {
                DetailRow(items[0])
                DetailRow(items[1])
                DetailRow(items[2])
            }

            // phân cách dọc
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(120.dp)
                    .padding(horizontal = 12.dp)
                    .background(borderColor)
            )

            Column(modifier = Modifier.weight(1f)) {
                DetailRow(items[3])
                DetailRow(items[4])
                DetailRow(items[5])
            }
        }
    }
}

data class DetailItem(
    val label: String,
    val value: String,
    val icon: ImageVector,
    val color: Color
)
@Composable
fun DetailRow(item: DetailItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // ICON + LABEL (chiếm 60%)
        Row(
            modifier = Modifier.weight(0.6f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(item.icon, contentDescription = null, tint = item.color, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(6.dp))
            Text(item.label, fontSize = 14.sp)
        }

        // VALUE (chiếm 40% và căn phải)
        Text(
            item.value,
            modifier = Modifier.weight(0.4f),
            textAlign = TextAlign.End,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 18.sp
        )
    }
}



@Composable
fun DetailRow(label: String, value: String, icon: ImageVector, iconColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                icon,
                contentDescription = null,
                tint = iconColor   // ⬅️ đổ màu tại đây
            )
            Spacer(Modifier.width(8.dp))
            Text(label)
        }

        Text(value, fontWeight = FontWeight.SemiBold)
    }
}

