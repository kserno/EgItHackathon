package pandas.com.egithackathon

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import pandas.com.egithackathon.location.LocationProvider
import pandas.com.egithackathon.model.Atm
import pandas.com.egithackathon.model.AtmModel
import javax.inject.Inject

/**
 *  Created by filipsollar on 19.10.18
 */
class DataService @Inject constructor(
        val context: Context,
        val moshi: Moshi,
        val locationProvider: LocationProvider
): IDataService {

    override fun getNearbyAtms(): List<AtmModel> {
        val rawString = context.resources.openRawResource(R.raw.atm_data).bufferedReader().use { it.readText() }

        val adapter = moshi.adapter<List<Atm>>(Types.newParameterizedType(List::class.java, Atm::class.java))

        return AtmMapper.mapAtms(adapter.fromJson(rawString) ?: emptyList(), locationProvider)
    }

}