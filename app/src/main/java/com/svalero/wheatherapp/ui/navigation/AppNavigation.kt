package com.svalero.wheatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.svalero.wheatherapp.ui.splash.SplashScreen
import com.svalero.wheatherapp.ui.weatherinformation.WeatherInformationScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Routes.SplashScreen.routes
    ) {
        composable(Routes.SplashScreen.routes) {
            SplashScreen(navController)
        }
        composable(Routes.WeatherInformationScreen.routes) {
            WeatherInformationScreen()
        }
    }
}