package com.svalero.wheatherapp.data.datasource.remote

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class LocationDataSource @Inject constructor(
    @ApplicationContext  private val context: Context
) {

    fun getCurrentCity(): String {
        return try {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val geocoder = Geocoder(context, Locale.getDefault())
            if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                return ""
            }

            val providers = locationManager.getProviders(true)
            var lastKnownLocation: Location? = null

            for (provider in providers.reversed()) {
                val location = locationManager.getLastKnownLocation(provider)
                if (location != null) {
                    lastKnownLocation = location
                    break
                }
            }

            val longitude = lastKnownLocation?.longitude
            val latitude = lastKnownLocation?.latitude
            val addresses: List<Address>? =
                geocoder.getFromLocation(latitude ?: 0.0, longitude ?: 0.0, 1)
            if (!addresses.isNullOrEmpty()) {
                val address: Address = addresses[0]
                val city = address.locality
                return city
            }else {
                return ""
            }
        } catch (ex: java.lang.Exception) {
            ""
        }
    }

}