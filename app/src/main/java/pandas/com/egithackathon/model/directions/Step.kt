package pandas.com.egithackathon.model.directions

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Step(
        val distance: Distance,
        val duration: Duration
) : Parcelable
