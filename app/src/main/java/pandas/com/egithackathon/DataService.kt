package pandas.com.egithackathon

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import pandas.com.egithackathon.model.AtmModel
import javax.inject.Inject

/**
 *  Created by filipsollar on 19.10.18
 */
class DataService @Inject constructor(
        val context: Context,
        val moshi: Moshi
): IDataService {

    override fun getNearbyAtms(): List<AtmModel> {
        val rawString = context.resources.openRawResource(R.raw.atm_data).bufferedReader().use { it.readText() }

        val adapter = moshi.adapter<List<AtmModel>>(Types.newParameterizedType(List::class.java, AtmModel::class.java))

        return adapter.fromJson(rawString) ?: emptyList()
    }

}