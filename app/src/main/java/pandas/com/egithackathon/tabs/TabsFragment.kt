package pandas.com.egithackathon.tabs

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_tabs.*
import pandas.com.egithackathon.BaseFragment
import pandas.com.egithackathon.R
import pandas.com.egithackathon.atmlist.AtmListFragment
import pandas.com.egithackathon.map.MapFragment
import pandas.com.egithackathon.model.AtmModel

/**
 *  Created by filipsollar on 19.10.18
 */
class TabsFragment: BaseFragment() {

    override val layoutId: Int = R.layout.fragment_tabs

    lateinit var adapter: ViewPagerAdapter

    lateinit var viewModel: TabsViewModel

    override fun bindData(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(TabsViewModel::class.java)

        setHasOptionsMenu(true)

        val fragments = listOf(
                MapFragment(),
                AtmListFragment().apply { parentFragment = this@TabsFragment }
        )
        adapter = ViewPagerAdapter(fragmentManager!!, fragments)

        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.tabs, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuAr) {
            findNavController().navigate(R.id.action_tabsFragment_to_arNavFragment)
        }

        return super.onOptionsItemSelected(item)
    }

    inner class ViewPagerAdapter(
            fm: FragmentManager,
            val fragments: List<BaseFragment>
    ) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            if (position == 0) {
                return "Map"
            } else {
                return "Atms"
            }
        }
    }

    fun onAtmListClicked(model: AtmModel) {
        tabLayout.getTabAt(0)?.select()
    }
}