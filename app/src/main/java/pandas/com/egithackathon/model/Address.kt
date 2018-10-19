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
data class Address(
        val street: String,
        val zipCode: String,
        val city: String,
        val district: String,
        val federalState: String,
        val countryCode: String
) : Parcelable