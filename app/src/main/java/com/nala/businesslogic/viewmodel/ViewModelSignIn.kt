package com.nala.businesslogic.viewmodel

import android.widget.Toast
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoLoginResponse
import com.nala.view.MyApplication

class ViewModelSignIn(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoLoginResponse>(mApplication, isToShowErrors) {


    private val liveEventSignIn: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventForgotPass: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun getLiveEventSignIn(): SingleLiveEvent<Boolean> {
        return liveEventSignIn
    }


    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }

    fun getLiveEventForgotPass(): SingleLiveEvent<Boolean> {
        return liveEventForgotPass
    }


    fun clickSignIn() {
        liveEventSignIn.value = true
    }
    fun clickBackPress() {
        liveEventBackPress.value = true
    }

    fun clickForgotPass() {
        liveEventForgotPass.value = true
    }


    override fun networkCallData() {
        TODO("Not yet implemented")
    }

    override fun sendResponseBodyData(data: PojoLoginResponse?) {
        TODO("Not yet implemented")
    }


}