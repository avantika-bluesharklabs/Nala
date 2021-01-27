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

class ViewModelMassageTechnique(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoCommonResponse>(mApplication, isToShowErrors) {

    private val liveEventForgotPass: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()




    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }





    fun clickBackPress() {
        liveEventBackPress.value = true
    }








    override fun networkCallData() {


    }

    override fun sendResponseBodyData(data: PojoCommonResponse?) {
    }

}