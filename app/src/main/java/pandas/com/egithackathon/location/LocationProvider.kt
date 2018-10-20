package pandas.com.egithackathon.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

/**
 *  Created by filipsollar on 19.10.18
 */
class LocationProvider @Inject constructor(
    context : Context
) : LocationCallback(){
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val location = MutableLiveData<Location?>().apply {
        value = Location("").apply {
            latitude = 48.1723588
            longitude = 17.142705
        }
    }

    init {
        if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==  PackageManager.PERMISSION_GRANTED) {

            val request = LocationRequest().apply { interval = 10000L }

            fusedLocationClient.requestLocationUpdates(request, this, null)
        }

    }

    override fun onLocationResult(p: LocationResult) {
        location.value = p.lastLocation
    }
}