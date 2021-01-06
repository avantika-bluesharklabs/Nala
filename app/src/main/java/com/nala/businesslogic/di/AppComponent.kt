package com.nala.businesslogic.di

import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.nala.businesslogic.rx.SchedulerProvider
import com.nala.businesslogic.sharedpreference.UtilsSharedPreferences
import com.nala.view.MyApplication
import com.nala.view.activities.ActivityBase
import com.nala.view.fragments.FragmentBase
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Avantika Gadhiya on Mar, 30 2020 16:14.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(myApplication: MyApplication)

    fun inject(activityBase: ActivityBase)

    fun inject(fragmentBase: FragmentBase)

    fun getPreferences(): UtilsSharedPreferences

    fun getLocalBroadcastManager(): LocalBroadcastManager

    fun getSchedulerProvider(): SchedulerProvider
}