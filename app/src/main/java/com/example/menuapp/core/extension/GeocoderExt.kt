package com.example.menuapp.core.extension

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build

fun Geocoder.fetchAddress(
    location: Location,
    callback: (address: Address?) -> Unit
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getFromLocation(location.latitude, location.longitude, 1) {
            callback.invoke(it.firstOrNull())
        }
    } else {
        @Suppress("DEPRECATION")
        try {
            callback.invoke(
                this.getFromLocation(location.latitude, location.longitude, 1)?.firstOrNull()
            )
        } catch (e: Exception) {
            //will catch if there is an internet problem
            e.printStackTrace()
            callback.invoke(null)
        }
    }
}