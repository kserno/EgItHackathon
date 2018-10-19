package pandas.com.egithackathon.model.directions

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Leg(
        val distance: Distance,
        val duration: Duration,
        @Json(name = "end_address") val endAddress: String,
        @Json(name = "end_location") val endLocation: Loc,
        @Json(name = "start_address") val startAddress: String,
        @Json(name = "start_location") val startLocation: Loc,
        val steps: List<Step> = emptyList()
) : Parcelable
