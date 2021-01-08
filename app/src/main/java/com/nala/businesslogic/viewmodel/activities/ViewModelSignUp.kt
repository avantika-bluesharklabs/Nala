package com.nala.businesslogic.viewmodel.activities

import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import com.nala.R
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoSignInResponse
import com.nala.businesslogic.pojo.PojoSignUpResponse
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.view.MyApplication

class ViewModelSignUp(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoSignUpResponse>(mApplication, isToShowErrors) {

    private val liveEventSignUp: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventCheckBox: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventUser: SingleLiveEvent<Int> = SingleLiveEvent()

    var observerName: ObservableString = ObservableString("")
    var observerErrorName = ObservableInt()
    var observerErrorEnabledName = ObservableBoolean(false)

    var observerEmail: ObservableString = ObservableString("")
    var observerErrorEmail = ObservableInt()
    var observerErrorEnabledEmail = ObservableBoolean(false)

    var observerPhone: ObservableString = ObservableString("")
    var observerErrorPhone = ObservableInt()
    var observerErrorEnabledPhone = ObservableBoolean(false)

    var observerPassword: ObservableString = ObservableString("")
    var observerErrorPassword = ObservableInt()
    var observerErrorEnabledPassword = ObservableBoolean(false)

    var observerConPassword: ObservableString = ObservableString("")
    var observerErrorConPassword = ObservableInt()
    var observerErrorEnabledConPassword = ObservableBoolean(false)

    fun getLiveEventSignUp(): SingleLiveEvent<Boolean> {
        return liveEventSignUp
    }

    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }

    fun getLiveEventUser(): SingleLiveEvent<Int> {
        return liveEventUser
    }


    fun getLiveEventCheckBox(): SingleLiveEvent<Boolean> {
        return liveEventCheckBox
    }


    fun clickSignUp() {

        val validateName = validateName()
        val validateEmail = validateEmail()
        val validatePassword = validatePassword()
        val validateConrPassword = validateConPassword()
        val validatePhone = validatePhone()
        liveEventSignUp.value =
            validateName && validateEmail && validatePhone && validatePassword && validateConrPassword
    }

    fun clickCheckbox() {
        liveEventCheckBox.value = true
    }

   fun clickBackPress() {
        liveEventBackPress.value = true
    }

    fun clickUser() {
        liveEventUser.value = 0
    }


    fun clickTherapist() {
        liveEventUser.value = 1
    }


    private fun validateName(): Boolean {

        return if (TextUtils.isEmpty(observerName.getTrimmed())) {
            observerErrorEnabledName.set(true)
            observerErrorName.set(R.string.error_blank_name)
            false
        } else {
            observerErrorEnabledName.set(false)
            true
        }
    }

    private fun validatePhone(): Boolean {

        return if (TextUtils.isEmpty(observerPhone.getTrimmed())) {
            observerErrorEnabledPhone.set(true)
            observerErrorPhone.set(R.string.error_blank_phone)
            false
        } else if (observerPhone.getTrimmedLength() < 10) {
            observerErrorEnabledPhone.set(true)
            observerErrorPhone.set(R.string.error_blank_phone_valid)
            false
        } else {
            observerErrorEnabledName.set(false)
            true
        }
    }

    private fun validateEmail(): Boolean {

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
        } else {
            observerErrorEnabledPassword.set(false)
            true
        }
    }

    private fun validateConPassword(): Boolean {
        return if (TextUtils.isEmpty(observerConPassword.getTrimmed())) {
            observerErrorEnabledConPassword.set(true)
            observerErrorConPassword.set(R.string.error_blank_confirm_password)
            false
        } else if (!observerConPassword.getTrimmed().equals(observerPassword.getTrimmed(), true)) {
            observerErrorEnabledConPassword.set(true)
            observerErrorConPassword.set(R.string.error_invalid_email)
            false
        } else {
            observerErrorEnabledConPassword.set(false)
            true
        }
    }

    override fun networkCallData() {

    }

    override fun sendResponseBodyData(data: PojoSignUpResponse?) {

    }

}