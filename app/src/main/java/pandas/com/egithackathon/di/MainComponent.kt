package pandas.com.egithackathon.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Component
import pandas.com.egithackathon.IDataService

/**
 *  Created by filipsollar on 19.10.18
 */
@Component(modules = [MainModule::class])
interface MainComponent {

    fun getContext(): Context

    fun getDataService(): IDataService
    fun getMoshi(): Moshi

}