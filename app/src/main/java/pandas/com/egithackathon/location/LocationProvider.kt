package pandas.com.egithackathon.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

/**
 *  Created by filipsollar on 19.10.18
 */
class LocationProvider @Inject constructor(
    context : Context
) {

    val location = MutableLiveData<Location?>()

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)


    init {
        if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==  PackageManager.PERMISSION_GRANTED) {

            fusedLocationClient.lastLocation.addOnSuccessListener {
                location.value = it
            }

        }

    }



}