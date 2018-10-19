package pandas.com.egithackathon.service

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 *  Created by filipsollar on 20.10.18
 */
class DirectionsService @Inject constructor(moshi: Moshi) {

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://maps.googleapis.com")
            .client(OkHttpClient.Builder()
                    .build())
            .build()


    private val directionsApi: DirectionsApi

    init {
        directionsApi = retrofit.create(DirectionsApi::class.java)
    }

    fun getDirections(originLat: Double, originLng: Double, destLat: Double, destLng: Double) =
            directionsApi.getDirections("$originLat,$originLng", "$destLat,$destLng")

}