package pandas.com.egithackathon.model.directions

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Distance(
        val text: String,
        val value: Int
) : Parcelable
