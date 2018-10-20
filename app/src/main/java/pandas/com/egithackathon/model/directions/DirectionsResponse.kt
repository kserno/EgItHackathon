package pandas.com.egithackathon.model.directions

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 *  Created by filipsollar on 19.10.18
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class DirectionsResponse(
        @Json(name = "geocoded_waypoints") val geocodedWaypoints: List<GeocodedWaypoint> = emptyList(),
        val routes: List<Route> = emptyList(),
        val status: String

): Parcelable