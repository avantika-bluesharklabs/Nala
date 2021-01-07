package com.nala.view

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.net.ConnectivityManager
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nala.R
import com.nala.businesslogic.di.AppComponent
import com.nala.businesslogic.di.AppModule
import com.nala.businesslogic.di.DaggerAppComponent
import com.nala.businesslogic.network.RetroFitCallFactory
import com.nala.businesslogic.network.RetroFitInterface
import com.nala.businesslogic.sharedpreference.UtilsSharedPreferences
import java.util.*


/**
 * Created by Avantika Gadhiya on 3/29/2020.
 */

class MyApplication : MultiDexApplication() {
    private var isLogShown: Boolean = true


    private lateinit var mAppComponent: AppComponent
    private lateinit var mRetrofitInterface: RetroFitInterface
    private lateinit var mPreferences: UtilsSharedPreferences

    fun showLogs(tag: String,msg: String){
        if(isLogShown){
            Log.e(tag,msg)
        }

    }


    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        mAppComponent.inject(this)
        initializeRetrofit()
        initSharedPrefrence()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        //checkVersion()
    }

    fun getAppComponent(): AppComponent {
        return mAppComponent
    }

    fun isInternetConnected(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                ?: return false

        val activeNetwork = connectivityManager.activeNetworkInfo ?: return false
        return activeNetwork.isConnected
    }

    private fun initializeRetrofit() {
        mRetrofitInterface = RetroFitCallFactory.create()
    }

    fun getRetroFitInterface(): RetroFitInterface? {
        return mRetrofitInterface
    }


    private fun initSharedPrefrence() {
        mPreferences = UtilsSharedPreferences(this)
    }


    fun glideCenterCircle(): RequestOptions? {
        return RequestOptions()
            .dontAnimate()
            .centerCrop()
           /* .placeholder(R.drawable.add_member_profile)
            .error(R.drawable.add_member_profile)*/
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        //.circleCrop()
    }

    fun glideCenterCircle(drawable: Int): RequestOptions? {
        return RequestOptions()
            .dontAnimate()
            .centerCrop()
            .placeholder(drawable)
            .error(drawable)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .circleCrop()
    }

    fun glideCenterCropCircle(drawable: Int): RequestOptions? {
        return RequestOptions()
            .dontAnimate()
            .centerCrop()
            .placeholder(drawable)
            .error(drawable)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .circleCrop()
    }

    fun glideOptionCenterProfileBitmap(): RequestOptions? {
        return RequestOptions()
            .dontAnimate()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .circleCrop()
    }
}