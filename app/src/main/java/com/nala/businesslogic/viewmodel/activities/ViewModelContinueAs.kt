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

class ViewModelContinueAs(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoCommonResponse>(mApplication, isToShowErrors) {

    private val liveEventTherapist: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventClient: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSignin: SingleLiveEvent<Boolean> = SingleLiveEvent()




    fun getLiveEventSignin(): SingleLiveEvent<Boolean> {
        return liveEventSignin
    }

    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }


    fun getLiveEvenTherapist(): SingleLiveEvent<Boolean> {
        return liveEventTherapist
    }

    fun getLiveEvenClient(): SingleLiveEvent<Boolean> {
        return liveEventClient
    }



    fun clickBackPress() {
        liveEventBackPress.value = true
    }

    fun clickheTherarapist(){

        liveEventTherapist.value = true
    }

    fun clickheClient(){

        liveEventClient.value = true
    }

    fun clickheSignin(){

        liveEventSignin.value = true
    }







    override fun networkCallData() {


    }

    override fun sendResponseBodyData(data: PojoCommonResponse?) {
    }

}