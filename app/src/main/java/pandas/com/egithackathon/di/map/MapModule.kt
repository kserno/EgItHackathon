package pandas.com.egithackathon.di.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import pandas.com.egithackathon.DataService
import pandas.com.egithackathon.IDataService
import pandas.com.egithackathon.location.LocationProvider
import pandas.com.egithackathon.map.MapView
import pandas.com.egithackathon.map.MapViewModel
import pandas.com.egithackathon.service.DirectionsService
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 *  Created by filipsollar on 19.10.18
 */
@Module
class MapModule(
        @get:Provides val mapView: MapView
)
class MapViewModelFactory @Inject constructor(
    val mapView: MapView,
    val dataService: IDataService,
    val locationProvider: LocationProvider,
    val directionsService: DirectionsService
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(mapView, dataService, locationProvider, directionsService) as T
        } else {
            throw IllegalArgumentException()
        }
    }

}