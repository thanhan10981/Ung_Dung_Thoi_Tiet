package com.example.ung_dung_thoi_tiet.ui.home
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ung_dung_thoi_tiet.ui.components.*
import com.example.ung_dung_thoi_tiet.utils.weatherBackgroundRes
import com.example.ung_dung_thoi_tiet.utils.weatherText
import java.time.LocalDateTime
import java.time.LocalDate
import androidx.compose.foundation.background
import com.example.ung_dung_thoi_tiet.model.DailyForecast
import com.example.ung_dung_thoi_tiet.model.HourlyForecast


@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = viewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    val isLoading by viewModel.isLoading.collectAsState()
    val weatherState by viewModel.weather.collectAsState()
    val error by viewModel.error.collectAsState()
    val air by viewModel.airQuality.collectAsState()

    // ✅ COPY RA BIẾN LOCAL → FIX SMART CAST
    val weather = weatherState

    LaunchedEffect(Unit) {
        viewModel.loadDefaultCity()
    }

    /* =========================
       HOURLY DATA (24H)
       ========================= */
    val hourlyDataFromAPI: List<HourlyForecast> =
        weather?.hourly?.let { hourly ->

            val currentHour = LocalDateTime.now().hour

            val startIndex = hourly.time.indexOfFirst { time ->
                time.contains(String.format("%02d", currentHour))
            }.coerceAtLeast(0)

            (0 until 24).mapNotNull { offset ->
                val index = startIndex + offset
                if (index < hourly.time.size) {
                    HourlyForecast(
                        time = hourly.time[index].takeLast(5),
                        temperature = hourly.temperature_2m[index].toInt(),
                        humidity = hourly.relativehumidity_2m[index],
                        windSpeed = hourly.wind_speed_10m[index].toInt(),
                        weatherCode = hourly.weathercode.getOrNull(index) ?: 0
                    )
                } else null
            }
        } ?: emptyList()

    /* =========================
       DAILY DATA (7 DAYS)
       ========================= */
    val dailyForecastFromAPI: List<DailyForecast> =
        weather?.daily?.let { daily ->
            daily.time.mapIndexed { index, date ->

                val label = when (index) {
                    0 -> "Hôm nay"
                    1 -> "Ngày mai"
                    else -> {
                        val d = LocalDate.parse(date)
                        when (d.dayOfWeek) {
                            java.time.DayOfWeek.SUNDAY -> "Chủ nhật"
                            else -> "Thứ ${d.dayOfWeek.value + 1}"
                        }
                    }
                }

                DailyForecast(
                    dayLabel = label,
                    weatherCode = daily.weathercode[index],
                    status = weatherText(daily.weathercode[index]),
                    tempLow = daily.temperature_2m_min[index].toInt(),
                    tempHigh = daily.temperature_2m_max[index].toInt()
                )
            }
        } ?: emptyList()

    /* =========================
       UI
       ========================= */
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(WindowInsets.statusBars.asPaddingValues())
                    .padding(16.dp)
            ) {
                WeatherHeader(
                    query = viewModel.searchQuery,
                    isLoading = isLoading,
                    onQueryChange = viewModel::onQueryChange,
                    onSearch = {
                        viewModel.searchWeather(context)
                        keyboardController?.hide()
                    },
                    onRefresh = {
                        viewModel.refresh(context)
                        keyboardController?.hide()
                    }
                )
            }
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {

            /* =========================
               🌈 BACKGROUND ANIMATION
               ========================= */
            weather?.let { w ->
                val hour = LocalDateTime.now().hour
                val isNight = hour < 6 || hour >= 18

                WeatherAnimatedBackground(
                    rawRes = weatherBackgroundRes(
                        w.current_weather?.weathercode ?: 0,
                        isNight
                    )
                )
            }

            /* =========================
               🧊 OVERLAY CONTENT
               ========================= */
            when {
                isLoading -> {
                    LoadingView()
                }

                error != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = error ?: "Có lỗi xảy ra",
                            color = Color.Red
                        )
                    }
                }

                weather == null -> {
                    LoadingView()
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp)
                    ) {

                        Spacer(Modifier.height(16.dp))

                        val hour = LocalDateTime.now().hour
                        val isNight = hour < 6 || hour >= 18

                        /* ===== TODAY CARD ===== */
                        TodayCard(
                            city = viewModel.cityName,
                            temperature = weather.current_weather?.temperature?.toInt() ?: 0,
                            weatherCode = weather.current_weather?.weathercode ?: 0,
                            highTemp = weather.daily?.temperature_2m_max?.first()?.toInt() ?: 0,
                            lowTemp = weather.daily?.temperature_2m_min?.first()?.toInt() ?: 0,
                            isNight = isNight
                        )

                        Spacer(Modifier.height(20.dp))

                        /* ===== STATS ===== */
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color.White.copy(alpha = 0.85f),
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(vertical = 12.dp)
                        ) {
                            WeatherStatsGrid(weather = weather)
                        }



                        Spacer(Modifier.height(20.dp))

                        /* ===== INFO DETAIL ===== */
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color.White.copy(alpha = 0.85f),
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(12.dp)
                        ) {
                            InfoDetailCard(weather = weather)
                        }


                        Spacer(Modifier.height(20.dp))

                        /* ===== AIR QUALITY ===== */
                        air?.hourly?.let {
                            AtmosphereCard(
                                pm25 = it.pm2_5.firstOrNull() ?: 0.0,
                                pm10 = it.pm10.firstOrNull() ?: 0.0,
                                ozone = it.ozone.firstOrNull() ?: 0.0
                            )
                        }

                        Spacer(Modifier.height(20.dp))

                        /* ===== HOURLY ===== */
                        HourlyForecastSection(
                            hourly = hourlyDataFromAPI,
                            onSeeMore = { navController.navigate("forecast_detail") }
                        )

                        Spacer(Modifier.height(20.dp))

                        /* ===== 7 DAYS ===== */
                        SevenDayForecast(
                            daily = dailyForecastFromAPI
                        )

                        Spacer(Modifier.height(32.dp))
                    }
                }
            }
        }
    }
}