package com.example.ung_dung_thoi_tiet.ui.detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ung_dung_thoi_tiet.model.HourlyForecast

import kotlin.math.max
@Composable
fun TemperatureChart(
    data: List<HourlyForecast>
) {
    if (data.size < 2) return

    val temps = data.map { it.temperature }
    val maxTemp = (temps.maxOrNull() ?: 0) + 2
    val minTemp = (temps.minOrNull() ?: 0) - 2
    val tempRange = max(1, maxTemp - minTemp)

    val chartHeight = 180.dp
    val itemWidth = 36.dp
    val chartWidth = (data.size * itemWidth.value).dp

    val density = LocalDensity.current
    val textPaint = remember(density) {
        android.graphics.Paint().apply {
            color = android.graphics.Color.GRAY
            textSize = with(density) { 12.sp.toPx() }
            isAntiAlias = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {

        Text("Biểu đồ nhiệt độ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            Canvas(
                modifier = Modifier
                    .width(chartWidth)
                    .height(chartHeight)
            ) {

                val width = size.width
                val height = size.height
                val stepX = width / (data.size - 1)
                val topPaddingPx = 16.dp.toPx()

                fun tempToY(temp: Int): Float {
                    val ratio = (temp - minTemp).toFloat() / tempRange
                    return (height - topPaddingPx) - (ratio * (height - topPaddingPx))
                }


                val linePath = Path()
                val areaPath = Path()

                data.forEachIndexed { index, item ->
                    val x = stepX * index
                    val y = tempToY(item.temperature)

                    if (index == 0) {
                        linePath.moveTo(x, y)
                        areaPath.moveTo(x, height)
                        areaPath.lineTo(x, y)
                    } else {
                        linePath.lineTo(x, y)
                        areaPath.lineTo(x, y)
                    }
                }

                areaPath.lineTo(stepX * (data.size - 1), height)
                areaPath.close()

                // Gradient area
                drawPath(
                    areaPath,
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF64B5F6).copy(alpha = 0.3f),
                            Color.Transparent
                        )
                    )
                )

                // Line
                drawPath(
                    linePath,
                    Color(0xFF3B82F6),
                    style = Stroke(
                        width = 4.dp.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )

                // Points + temperature
                data.forEachIndexed { index, item ->
                    val x = stepX * index
                    val y = tempToY(item.temperature)

                    drawCircle(Color.White, 6.dp.toPx(), Offset(x, y))
                    drawCircle(Color(0xFF3B82F6), 3.dp.toPx(), Offset(x, y))

                    drawContext.canvas.nativeCanvas.drawText(
                        "${item.temperature}°",
                        x - 10.dp.toPx(),
                        (y - 10.dp.toPx()).coerceAtLeast(10.dp.toPx()),
                        textPaint
                    )

                }

                // X axis time
                val step = if (data.size > 24) 4 else 2
                data.forEachIndexed { index, item ->
                    if (index % step == 0) {
                        val x = stepX * index
                        drawContext.canvas.nativeCanvas.drawText(
                            item.time,
                            x - 14.dp.toPx(),
                            height + 14.dp.toPx(),
                            textPaint
                        )
                    }
                }
            }
        }
    }
}
