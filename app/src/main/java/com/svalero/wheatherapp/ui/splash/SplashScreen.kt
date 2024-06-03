package com.svalero.wheatherapp.ui.splash

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.svalero.wheatherapp.R
import com.svalero.wheatherapp.ui.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            navController.navigate(Routes.WeatherInformationScreen.routes)

        } else {
        }
    }
    val context = LocalContext.current

    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) -> {
            LaunchedEffect(key1 = true) {
                delay(500)
                navController.navigate(Routes.WeatherInformationScreen.routes)
            }
        }
        else -> {
            LaunchedEffect(key1 = true) {
                delay(500)
                launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_weather_app),
            contentDescription = null
        )

    }
}