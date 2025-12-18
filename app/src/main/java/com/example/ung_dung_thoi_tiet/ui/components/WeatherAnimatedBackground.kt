package com.example.ung_dung_thoi_tiet.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*

@Composable
fun WeatherAnimatedBackground(
    rawRes: Int
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(rawRes)
    )

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.fillMaxSize()
    )
}
