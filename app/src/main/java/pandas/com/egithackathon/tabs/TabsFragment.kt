package pandas.com.egithackathon.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.fragment_tabs.*
import pandas.com.egithackathon.BaseFragment
import pandas.com.egithackathon.R
import pandas.com.egithackathon.atmlist.AtmListFragment
import pandas.com.egithackathon.map.MapFragment

/**
 *  Created by filipsollar on 19.10.18
 */
class TabsFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_tabs

    lateinit var adapter: ViewPagerAdapter

    override fun bindData(view: View, savedInstanceState: Bundle?) {


        val fragments = listOf(MapFragment(), AtmListFragment())
        adapter = ViewPagerAdapter(fragmentManager!!, fragments)

        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
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
}