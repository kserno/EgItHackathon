package pandas.com.egithackathon.model.directions

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Duration(
        val text: String,
        val value: Int
) : Parcelable
