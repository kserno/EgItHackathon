package pandas.com.egithackathon.di.ar

import dagger.Component
import pandas.com.egithackathon.ar.ArNavFragment
import pandas.com.egithackathon.di.MainComponent

/**
 *  Created by filipsollar on 20.10.18
 */
@Component(dependencies = [MainComponent::class], modules = [ArNavModule::class])
interface ArNavComponent {

    fun inject(fragment: ArNavFragment)

}