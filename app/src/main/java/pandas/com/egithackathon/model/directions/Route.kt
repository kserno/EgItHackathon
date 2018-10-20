package pandas.com.egithackathon.model.directions

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Route(
        val copyrights: String,
        val legs: List<Leg> = emptyList(),
        @Json(name = "overview_polyline") val overviewPolyline: Poly
): Parcelable
