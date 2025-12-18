package com.example.ung_dung_thoi_tiet.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices

object LocationUtils {

    fun getCurrentLocation(
        context: Context,
        onResult: (Double, Double) -> Unit
    ) {
        val client = LocationServices.getFusedLocationProviderClient(context)

        if (
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        client.lastLocation.addOnSuccessListener { location ->
            location?.let {
                onResult(it.latitude, it.longitude)
            }
        }
    }
}
