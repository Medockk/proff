package com.example.matule

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus



class PointObj(context: Context, activity: Activity){
    val context = context
    val activity = activity
    companion object{
        @JvmStatic var myLatitude = mutableStateOf(0.0)
        @JvmStatic var myLongitude = mutableStateOf(0.0)
    }
    fun location(){
        val locationListener = object : LocationListener{
            override fun onLocationUpdated(location: Location) {
                myLatitude.value = location.position.latitude
                myLongitude.value = location.position.longitude
            }

            override fun onLocationStatusUpdated(p0: LocationStatus) {

            }
        }
    }

    fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION")
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                "android.permission.ACCESS_COARSE_LOCATION"
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(
                    "android.permission.ACCESS_COARSE_LOCATION",
                    "android.permission.ACCESS_FINE_LOCATION"
                ), 0
            )
            return
        }

    }
}