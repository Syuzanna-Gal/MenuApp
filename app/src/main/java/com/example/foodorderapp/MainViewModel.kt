package com.example.foodorderapp

import android.app.Application
import android.location.Geocoder
import android.location.Location
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coreui.util.DATE_LOCALE_TYPE
import com.example.domain.delegate.CurrentAddressDelegate
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val currentAddressDelegate: CurrentAddressDelegate,
) : AndroidViewModel(application) {

    private val locationClient by lazy {
        LocationServices.getFusedLocationProviderClient(application)
    }

    private val geocoder by lazy {
        Geocoder(application, DATE_LOCALE_TYPE)
    }

    @RequiresPermission(
        anyOf = [android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION],
    )
    fun fetchCurrentLocation(usePreciseLocation: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val priority =
                if (usePreciseLocation) Priority.PRIORITY_HIGH_ACCURACY else Priority.PRIORITY_BALANCED_POWER_ACCURACY
            val result = locationClient.getCurrentLocation(
                priority,
                CancellationTokenSource().token
            ).await()

            getAddressFromLocation(result)
        }
    }

    //TODO: geocoder extension
    private fun getAddressFromLocation(location: Location) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            geocoder.getFromLocation(location.latitude, location.longitude, 1) {
                it.firstOrNull()?.let {
                    viewModelScope.launch { currentAddressDelegate.setCurrentAddress(it) }
                }
            }
        } else {
            @Suppress("DEPRECATION")
            try {
                geocoder.getFromLocation(location.latitude, location.longitude, 1)?.firstOrNull()
                    ?.let {
                        viewModelScope.launch { currentAddressDelegate.setCurrentAddress(it) }
                    }
            } catch (e: Exception) {
                //will catch if there is an internet problem
                e.printStackTrace()
            }
        }
    }
}