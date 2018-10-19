package pandas.com.egithackathon.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import pandas.com.egithackathon.DataService
import pandas.com.egithackathon.IDataService

/**
 *  Created by filipsollar on 19.10.18
 */
@Module
class MainModule(
        @get:Provides val context: Context,
        @get:Provides val moshi: Moshi
) {

    @Provides
    fun getDataService(dataService: DataService): IDataService = dataService

}