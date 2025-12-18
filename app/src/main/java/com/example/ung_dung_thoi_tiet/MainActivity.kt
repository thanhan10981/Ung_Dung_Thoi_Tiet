package com.example.ung_dung_thoi_tiet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.ung_dung_thoi_tiet.ui.detail.ForecastDetailScreen
import com.example.ung_dung_thoi_tiet.ui.home.WeatherScreen
import com.example.ung_dung_thoi_tiet.ui.home.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {

                // ✅ TẠO 1 NAV CONTROLLER DUY NHẤT
                val navController = rememberNavController()

                // ✅ NAV HOST
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {

                    composable("home") {
                        WeatherScreen(navController)
                    }

                    composable("forecast_detail") { backStackEntry ->
                        val parentEntry = remember(backStackEntry) {
                            navController.getBackStackEntry("home")
                        }

                        val sharedViewModel: WeatherViewModel =
                            viewModel(parentEntry)

                        ForecastDetailScreen(
                            onBack = { navController.popBackStack() },
                            viewModel = sharedViewModel
                        )
                    }
                }
            }
        }
    }
}
