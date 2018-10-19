package pandas.com.egithackathon

import android.app.Application
import com.squareup.moshi.Moshi
import pandas.com.egithackathon.di.DaggerMainComponent
import pandas.com.egithackathon.di.MainComponent
import pandas.com.egithackathon.di.MainModule

/**
 *  Created by filipsollar on 19.10.18
 */
class BaseApplication : Application() {

    lateinit var component: MainComponent


    override fun onCreate() {
        super.onCreate()

        component = DaggerMainComponent.builder()
                .mainModule(MainModule(this, Moshi.Builder().build()))
                .build()

    }
}