package pandas.com.egithackathon

import pandas.com.egithackathon.model.AtmModel

/**
 *  Created by filipsollar on 19.10.18
 */
interface IDataService {

    fun getNearbyAtms() : List<AtmModel>

}