package pandas.com.egithackathon

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import pandas.com.egithackathon.location.LocationProvider
import pandas.com.egithackathon.model.Atm
import pandas.com.egithackathon.model.AtmModel

/**
 *  Created by filipsollar on 19.10.18
 */
object AtmMapper {

    fun mapAtms(atms: List<Atm>, locationProvider: LocationProvider): List<AtmModel> {
        val result = mutableListOf<AtmModel>()

        atms.forEach {
            result.add(mapAtm(it, locationProvider))
        }

        result.sortBy { it.distanceLen }

        return result
    }

    fun mapAtm(atm: Atm, locationProvider: LocationProvider): AtmModel {
        val address = atm.address.street + ", " + atm.address.city

        val myLoc = locationProvider.location.value

        val destLoc = Location("")
        destLoc.latitude = atm.location.latitude
        destLoc.longitude = atm.location.longitude

        val distance = myLoc!!.distanceTo(destLoc)

        return AtmModel(
                atm.objectId,
                address,
                atm.location,
                atm.standalone,
                distance.toInt().toString() + " m",
                distance,
                atm.category,
                atm.institute,
                atm.features
        )
    }





}