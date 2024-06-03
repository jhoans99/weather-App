package com.svalero.wheatherapp.ui.navigation

sealed class Routes(val routes: String) {
    object SplashScreen: Routes("splash_screen")
    object WeatherInformationScreen: Routes("weather_information_screen")

}