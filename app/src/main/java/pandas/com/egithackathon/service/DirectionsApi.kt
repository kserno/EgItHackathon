package pandas.com.egithackathon.service

import io.reactivex.Single
import pandas.com.egithackathon.model.directions.DirectionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Created by filipsollar on 20.10.18
 */
interface DirectionsApi {

    @GET("maps/api/directions/json?key=AIzaSyCaZlnQ3E9vT8TIcFgNckZdrfpHVODapb4&mode=walking")
    fun getDirections(
            @Query("origin") origin: String,
            @Query("destination") destination: String
    ): Single<DirectionsResponse>
}