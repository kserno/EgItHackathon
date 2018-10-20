package pandas.com.egithackathon.ar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pandas.com.egithackathon.IDataService
import pandas.com.egithackathon.location.LocationProvider
import pandas.com.egithackathon.model.AtmModel
import pandas.com.egithackathon.service.DirectionsService

/**
 *  Created by filipsollar on 20.10.18
 */
class ArNavViewModel(
        private val arNavView: ArNavView,
        private val dataService: IDataService,
        private val directionsService: DirectionsService,
        private val locationProvider: LocationProvider
): ViewModel() {

    val atmList = MutableLiveData<List<AtmModel>>().apply {
        value = dataService.getNearbyAtms().take(5)
    }

    fun onAtmClicked(atmModel: AtmModel) {
        val location = locationProvider.location


    }


}