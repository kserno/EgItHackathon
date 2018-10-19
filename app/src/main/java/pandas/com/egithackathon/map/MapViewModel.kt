package pandas.com.egithackathon.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pandas.com.egithackathon.IDataService
import pandas.com.egithackathon.location.LocationProvider
import pandas.com.egithackathon.model.AtmModel

/**
 *  Created by filipsollar on 19.10.18
 */
class MapViewModel(
        val mapView: MapView,
        val dataService: IDataService,
        val locationProvider: LocationProvider
) : ViewModel() {

    val atms = MutableLiveData<List<AtmModel>>().apply {
        value = dataService.getNearbyAtms()
    }

    val location
            get() = locationProvider.location

}