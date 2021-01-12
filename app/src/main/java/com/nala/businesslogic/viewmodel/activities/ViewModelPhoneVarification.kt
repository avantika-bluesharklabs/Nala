package com.nala.businesslogic.viewmodel.activities

import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import com.nala.R
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.view.MyApplication

class ViewModelPhoneVarification(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoCommonResponse>(mApplication, isToShowErrors) {

    private val liveEventSubmit: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()

    var mOtp =""
    var observerEmail: ObservableString = ObservableString("")
    var observerErrorEmail = ObservableInt()
    var observerErrorEnabledEmail = ObservableBoolean(false)

    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }

    fun getLiveEventSubmit(): SingleLiveEvent<Boolean> {
        return liveEventSubmit
    }


    fun clickBackPress() {
        liveEventBackPress.value = true
    }

    fun clickSubmit() {
        liveEventSubmit.value = true
    }



    override fun networkCallData() {


    }

    override fun sendResponseBodyData(data: PojoCommonResponse?) {
    }
}