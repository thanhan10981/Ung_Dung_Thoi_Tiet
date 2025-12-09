package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ung_dung_thoi_tiet.R



@Composable
fun TodayCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray)
            Text("Bình Định, Việt Nam", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }

        Text(
            "Cập nhật lần cuối: 00:35",
            fontSize = 13.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.Gray
        )

        Spacer(Modifier.height(16.dp))

        WeatherAnimation(
            rawRes = R.raw.sunicon,
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.CenterHorizontally)
        )


        Spacer(Modifier.height(12.dp))

        Text(
            "28°",
            fontSize = 54.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            "Nắng ít mây",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Cao: 32°", fontSize = 16.sp)
            Spacer(Modifier.width(24.dp))   // chỉnh to/nhỏ theo ý bạn
            Text("Thấp: 24°", fontSize = 16.sp)
        }

    }
}
