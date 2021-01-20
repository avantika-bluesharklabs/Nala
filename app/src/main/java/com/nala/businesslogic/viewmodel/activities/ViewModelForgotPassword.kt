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

class ViewModelForgotPassword(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoCommonResponse>(mApplication, isToShowErrors) {

    private val liveEventForgotPass: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()


    var observerEmail: ObservableString = ObservableString("")
    var observerErrorEmail = ObservableInt()
    var observerErrorEnabledEmail = ObservableBoolean(false)

    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }


    fun getLiveEventForgotPass(): SingleLiveEvent<Boolean> {
        return liveEventForgotPass
    }


    fun clickBackPress() {
        liveEventBackPress.value = true
    }

    fun clickForgotPass() {
        val validateEmail = validateUserData()
        liveEventForgotPass.value = validateEmail
    }


    private fun validateUserData(): Boolean {

        return if (TextUtils.isEmpty(observerEmail.getTrimmed())) {
            observerErrorEnabledEmail.set(true)
            observerErrorEmail.set(R.string.error_blank_email)
            mApplication!!.showLogs("SignInCLick", "IF")
            false
        }
//        else if (isEmailValid(observerEmail.getTrimmed())) {
//            mApplication!!.showLogs("SignInCLick", "ElseIF")
//            observerErrorEnabledEmail.set(true)
//            observerErrorEmail.set(R.string.error_invalid_email)
//            false
//        }
        else {
            observerErrorEnabledEmail.set(false)
            true
        }
    }

    fun isEmailValid(email: String): Boolean {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    override fun networkCallData() {


    }

    override fun sendResponseBodyData(data: PojoCommonResponse?) {
    }

}