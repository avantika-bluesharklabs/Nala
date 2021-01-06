package com.nala.businesslogic.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Avantika Gadhiya on 3/31/2020.
 */
class AppSchedulerProvider : SchedulerProvider {
    override fun ui(): Scheduler? {
        return AndroidSchedulers.mainThread()
    }

    override fun computation(): Scheduler? {
        return Schedulers.computation()
    }

    override fun io(): Scheduler? {
        return Schedulers.io()
    }
}