package pandas.com.egithackathon.di.map

import dagger.Component
import pandas.com.egithackathon.di.MainComponent
import pandas.com.egithackathon.map.MapFragment

/**
 *  Created by filipsollar on 19.10.18
 */
@Component(modules = [MapModule::class], dependencies = [MainComponent::class])
interface MapComponent {

    fun inject(fragment: MapFragment)
}