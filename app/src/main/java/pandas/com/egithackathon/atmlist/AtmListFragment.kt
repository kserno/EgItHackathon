package pandas.com.egithackathon.atmlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_atm_list.*
import pandas.com.egithackathon.BaseFragment
import pandas.com.egithackathon.R
import pandas.com.egithackathon.di.atmlist.AtmListModule
import pandas.com.egithackathon.di.atmlist.AtmListViewModelFactory
import pandas.com.egithackathon.di.atmlist.DaggerAtmListComponent
import pandas.com.egithackathon.model.AtmModel
import javax.inject.Inject

/**
 *  Created by filipsollar on 19.10.18
 */
class AtmListFragment: BaseFragment(), AtmListView {
    override val layoutId: Int = R.layout.fragment_atm_list

    @Inject lateinit var factory : AtmListViewModelFactory

    lateinit var viewModel: AtmListViewModel

    val adapter: AtmListAdapter = AtmListAdapter()

    override fun bindData(view: View, savedInstanceState: Bundle?) {


        DaggerAtmListComponent.builder()
                .atmListModule(AtmListModule(this))
                .mainComponent(application!!.component)
                .build()
                .inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(AtmListViewModel::class.java)


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.atmList.observe(this, Observer<List<AtmModel>> {
            adapter.setData(it)
        })


    }
}