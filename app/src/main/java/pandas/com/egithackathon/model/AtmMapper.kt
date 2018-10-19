package pandas.com.egithackathon.model

import pandas.com.egithackathon.location.LocationProvider

/**
 *  Created by filipsollar on 19.10.18
 */
object AtmMapper {

    fun mapAtms(atms: List<Atm>): List<AtmModel> {
        val result = mutableListOf<AtmModel>()

        atms.forEach {
            result.add(mapAtm(it))
        }

        return result
    }

    fun mapAtm(atm: Atm): AtmModel {
        val address = atm.address.street + ", " + atm.address.city

        val distance = 0

        return AtmModel(
                atm.objectId,
                address,
                atm.location,
                atm.standalone,
                53.2,
                atm.category,
                atm.institute,
                atm.features
        )
    }





}