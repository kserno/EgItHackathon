package pandas.com.egithackathon.atmlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pandas.com.egithackathon.DataService
import pandas.com.egithackathon.IDataService
import pandas.com.egithackathon.model.AtmModel

/**
 *  Created by filipsollar on 19.10.18
 */
class AtmListViewModel(
        val atmListView: AtmListView,
        val dataService: IDataService
): ViewModel() {


    val atmList = MutableLiveData<List<AtmModel>>().apply { value = dataService.getNearbyAtms() }


}