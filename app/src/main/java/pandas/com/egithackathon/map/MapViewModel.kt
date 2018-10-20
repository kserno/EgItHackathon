package pandas.com.egithackathon.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pandas.com.egithackathon.IDataService
import pandas.com.egithackathon.applySchedulers
import pandas.com.egithackathon.location.LocationProvider
import pandas.com.egithackathon.model.AtmModel
import pandas.com.egithackathon.service.DirectionsService

/**
 *  Created by filipsollar on 19.10.18
 */
class MapViewModel(
        val mapView: MapView,
        val dataService: IDataService,
        val locationProvider: LocationProvider,
        val directionsService: DirectionsService
) : ViewModel() {

    val atms = MutableLiveData<List<AtmModel>>().apply {
        value = dataService.getNearbyAtms()
    }

    val location
            get() = locationProvider.location

    fun onMarkerClick(lat: Double, lng: Double) {

        directionsService.getDirections(location.value!!.latitude, location.value!!.longitude, lat, lng)
                .applySchedulers()
                .doOnSuccess {
                    mapView.showPolyLine(it.routes[0].overviewPolyline.points)
                }
                .doOnError { it.printStackTrace() }
                .subscribe()
    }

}