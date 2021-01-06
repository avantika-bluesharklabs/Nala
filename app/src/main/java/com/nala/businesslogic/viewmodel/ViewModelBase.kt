package com.nala.businesslogic.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.rx.SchedulerProvider
import com.nala.view.MyApplication

/**
 * Created by Avantika Gadhiya on 3/29/2020.
 */
open class ViewModelBase {
    val observableShowLoading = ObservableBoolean(false)
    val observerSnackBarInt = ObservableInt()
    val observerSnackBarString = ObservableString("")
    val mSchedulerProvider: SchedulerProvider? = null
    var mApplication: MyApplication

    constructor(mApplication: MyApplication) {
        this.mApplication = mApplication
    }
}