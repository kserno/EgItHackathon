package pandas.com.egithackathon.di.ar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import pandas.com.egithackathon.DataService
import pandas.com.egithackathon.IDataService
import pandas.com.egithackathon.ar.ArNavView
import pandas.com.egithackathon.ar.ArNavViewModel
import pandas.com.egithackathon.location.LocationProvider
import pandas.com.egithackathon.service.DirectionsService
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 *  Created by filipsollar on 20.10.18
 */
@Module
class ArNavModule(
        @get:Provides val arNavView: ArNavView
)

class ArNavViewModelFactory @Inject constructor(
        val arNavView: ArNavView,
        val dataService: IDataService,
        val directionsService: DirectionsService,
        val locationProvider: LocationProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArNavViewModel::class.java)) {
            return ArNavViewModel(arNavView, dataService,  directionsService, locationProvider) as T
        } else {
            throw IllegalArgumentException()
        }
    }



}