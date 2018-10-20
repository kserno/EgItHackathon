package pandas.com.egithackathon.model.directions

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class GeocodedWaypoint(
        @Json(name = "geocoder_status") val geocoderStatus: String,
        @Json(name = "place_id") val placeId: String,
        val types: List<String> = emptyList()
) : Parcelable
