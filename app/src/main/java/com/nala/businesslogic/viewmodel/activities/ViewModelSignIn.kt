package com.nala.businesslogic.viewmodel.activities

import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import com.nala.R
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoSignInResponse
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.view.MyApplication

class ViewModelSignIn(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoSignInResponse>(mApplication, isToShowErrors) {


    private val liveEventSignIn: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventForgotPass: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()


    var observerEmail: ObservableString = ObservableString("")
    var observerErrorEmail = ObservableInt()
    var observerErrorEnabledEmail = ObservableBoolean(false)

    var observerPassword: ObservableString = ObservableString("")
    var observerErrorPassword = ObservableInt()
    var observerErrorEnabledPassword = ObservableBoolean(false)


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
        val validateEmail = validateUserData()
        val validatePassword = validatePassword()
        liveEventSignIn.value = validateEmail && validatePassword
    }

    fun clickBackPress() {
        liveEventBackPress.value = true
    }

    fun clickForgotPass() {
        liveEventForgotPass.value = true
    }


    override fun networkCallData() {
        setIsToShowErrors(true)
    }

    override fun sendResponseBodyData(data: PojoSignInResponse?) {
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

    private fun validatePassword(): Boolean {
        return if (TextUtils.isEmpty(observerPassword.getTrimmed())) {
            observerErrorEnabledPassword.set(true)
            observerErrorPassword.set(R.string.error_blank_password)
            false
        } /*else if (!Utils.isValidPassword(observerPassword.getTrimmed())) {
                 observerErrorEnabledPassword.set(true);
                 observerErrorPassword.set(R.string.error_invalid_password);
                 return false;
             }*/ else {
            observerErrorEnabledPassword.set(false)
            true
        }
    }

    fun isEmailValid(email: String): Boolean {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}