package pandas.com.egithackathon.di.atmlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import pandas.com.egithackathon.DataService
import pandas.com.egithackathon.IDataService
import pandas.com.egithackathon.atmlist.AtmListView
import pandas.com.egithackathon.atmlist.AtmListViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 *  Created by filipsollar on 19.10.18
 */
@Module
class AtmListModule(
        @get:Provides val atmListView: AtmListView
)

class AtmListViewModelFactory @Inject constructor(
        val atmListView: AtmListView,
        val dataService: IDataService
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AtmListViewModel::class.java)) {
            return AtmListViewModel(atmListView, dataService) as T
        } else {
            throw IllegalArgumentException()
        }


    }

}