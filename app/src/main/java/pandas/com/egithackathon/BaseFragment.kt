package pandas.com.egithackathon

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *  Created by filipsollar on 19.10.18
 */
abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData(view, savedInstanceState)
    }

    abstract fun bindData(view: View, savedInstanceState: Bundle?)

    protected val application: BaseApplication?
        get() {
            if (activity != null) {
                if (activity!!.application != null && activity!!.application is BaseApplication) {
                    return activity!!.application as BaseApplication
                }
            }
            return null
        }
}