package com.example.ung_dung_thoi_tiet.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ung_dung_thoi_tiet.utils.currentTime

@Composable
fun ForecastDetailHeader(
    city: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(
                start = 0.dp,
                end = 12.dp,
                top = 8.dp,
                bottom = 6.dp
            )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onBack() }
                    .padding(start = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }

            Spacer(Modifier.width(4.dp))

            Text(
                text = "Dự báo chi tiết 24–48 giờ",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = "$city • Cập nhật ${currentTime()}",
            fontSize = 13.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 36.dp)
        )
    }
}
