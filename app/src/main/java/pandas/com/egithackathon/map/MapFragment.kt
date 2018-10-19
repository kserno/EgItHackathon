package pandas.com.egithackathon.map

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import pandas.com.egithackathon.BaseFragment
import pandas.com.egithackathon.R
import pandas.com.egithackathon.di.MainModule
import pandas.com.egithackathon.di.map.DaggerMapComponent
import pandas.com.egithackathon.di.map.MapComponent
import pandas.com.egithackathon.di.map.MapModule
import pandas.com.egithackathon.di.map.MapViewModelFactory
import javax.inject.Inject

/**
 *  Created by filipsollar on 19.10.18
 */
class MapFragment: BaseFragment(), MapView {

    @Inject
    lateinit var factory: MapViewModelFactory

    lateinit var viewModel: MapViewModel

    override val layoutId: Int = R.layout.fragment_map


    override fun bindData(view: View, savedInstanceState: Bundle?) {

        DaggerMapComponent.builder()
                .mainComponent(application!!.component)
                .mapModule(MapModule(this))
                .build()
                .inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(MapViewModel::class.java)


    }
}