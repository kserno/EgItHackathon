package pandas.com.egithackathon

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *  Created by filipsollar on 20.10.18
 */


fun <T> Single<T>.applySchedulers() = subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
