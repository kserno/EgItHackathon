package pandas.com.egithackathon.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 19.10.18
 */
@JsonClass(generateAdapter = true)
@Parcelize
data class AtmModel(
        val objectId: String,
        val address: Address,
        val location: Location,
        val standalone: Boolean,
        val category: String,
        val loadedOn: String,
        val institute: Institute,
        val features: List<String> = emptyList(),
        val origin: String
): Parcelable