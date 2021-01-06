package com.nala.businesslogic.di

import android.content.Context
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.nala.businesslogic.network.RetroFitCallFactory
import com.nala.businesslogic.network.RetroFitInterface
import com.nala.businesslogic.rx.AppSchedulerProvider
import com.nala.businesslogic.rx.SchedulerProvider
import com.nala.businesslogic.sharedpreference.UtilsSharedPreferences
import com.nala.view.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Avantika Gadhiya on Mar, 30 2020 16:10.
 */
@Module
class AppModule(myApplication: MyApplication) {
    var mApplication: MyApplication = myApplication

    @Provides
    fun providesApplicationContext(): Context {
        return mApplication
    }

    @Provides
    @Singleton
    fun providesMyApplication(): MyApplication {
        return mApplication
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): UtilsSharedPreferences {
        return UtilsSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun providesRetrofitInterface(): RetroFitInterface {
        return RetroFitCallFactory.create()
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun providesLocalBroadcastInstance(): LocalBroadcastManager {
        return LocalBroadcastManager.getInstance(mApplication!!)
    }
}