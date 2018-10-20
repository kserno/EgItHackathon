package pandas.com.egithackathon.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 19.10.18
 */
@Parcelize
data class AtmModel(
        val objectId: String,
        val address: String,
        val location: Location,
        val standalone: Boolean,
        val distance: String,
        val distanceLen: Float,
        val category: String,
        val institute: Institute,
        val features: List<String> = emptyList()
): Parcelable