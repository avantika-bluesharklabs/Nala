package com.nala.view.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nala.businesslogic.network.RetroFitInterface
import com.nala.businesslogic.sharedpreference.UtilsSharedPreferences
import com.nala.view.MyApplication
import com.nala.view.activities.ActivityBase
import javax.inject.Inject

/**
 * Created by Avantika Gadhiya on Mar, 30 2020 14:39.
 */

open class FragmentBase : Fragment() {
    @Inject
    lateinit var mPreferences: UtilsSharedPreferences

    @Inject
    lateinit var mRetroFitInterface: RetroFitInterface

    protected lateinit var mApplication: MyApplication
    protected lateinit var mActivity: ActivityBase
    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {

        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        requireActivity().window.statusBarColor = Color.TRANSPARENT

        super.onCreate(savedInstanceState)
        mActivity = activity as ActivityBase
        mApplication = mActivity.mApplication
        mApplication.getAppComponent().inject(this)
        mContext = context as Context
    }
}