package pandas.com.egithackathon.map

import androidx.lifecycle.ViewModel
import pandas.com.egithackathon.IDataService

/**
 *  Created by filipsollar on 19.10.18
 */
class MapViewModel(
        val mapView: MapView,
        val dataService: IDataService
) : ViewModel() {


    init {
        dataService.getNearbyAtms()
    }


}