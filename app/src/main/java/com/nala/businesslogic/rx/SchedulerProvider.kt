package com.nala.businesslogic.rx

import io.reactivex.Scheduler

/**
 * Created by Avantika Gadhiya on 3/31/2020.
 */
interface SchedulerProvider {
    fun ui(): Scheduler?

    fun computation(): Scheduler?

    fun io(): Scheduler?
}