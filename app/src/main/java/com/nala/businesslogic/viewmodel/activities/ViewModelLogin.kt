package com.nala.businesslogic.viewmodel.activities

import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.viewmodel.ViewModelBase
import com.nala.view.MyApplication

class ViewModelLogin(
    mApplication: MyApplication,
) : ViewModelBase(mApplication) {


    private val liveEventSignIn: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSignUp: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSignInFacebook: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSignInApple: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSignInGoogle: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun getLiveEventSignIn(): SingleLiveEvent<Boolean> {
        return liveEventSignIn
    }

    fun getLiveEventSignUp(): SingleLiveEvent<Boolean> {
        return liveEventSignUp
    }

    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }

    fun getLiveEventSignInFacebook(): SingleLiveEvent<Boolean> {
        return liveEventSignInFacebook
    }

    fun getLiveEventSignInApple(): SingleLiveEvent<Boolean> {
        return liveEventSignInApple
    }

    fun getLiveEventSignInGoogle(): SingleLiveEvent<Boolean> {
        return liveEventSignInGoogle
    }

    fun clickSignIn() {
        liveEventSignIn.value = true

    }

    fun clickSignInFacebook() {
        liveEventSignInFacebook.value = true
    }

    fun clickSignInApple() {
        liveEventSignInApple.value = true
    }

    fun clickSignInGoogle() {
        liveEventSignInGoogle.value = true
    }

    fun clickBackPress() {
        liveEventBackPress.value = true
    }


    fun clickSignUp() {
        liveEventSignUp.value = true
    }


}