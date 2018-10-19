package pandas.com.egithackathon.di.atmlist

import dagger.Component
import pandas.com.egithackathon.atmlist.AtmListFragment
import pandas.com.egithackathon.di.MainComponent

/**
 *  Created by filipsollar on 19.10.18
 */
@Component(modules = [AtmListModule::class], dependencies = [MainComponent::class])
interface AtmListComponent {

    fun inject(fragment: AtmListFragment)

}