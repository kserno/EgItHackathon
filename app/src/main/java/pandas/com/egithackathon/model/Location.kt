package pandas.com.egithackathon.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 19.10.18
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Location(
    val latitude: Double,
    val longitude: Double
) : Parcelable