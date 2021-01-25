package com.nala.businesslogic.viewmodel.activities

import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.nala.R
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoAddPersonalDetails
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.view.MyApplication
import java.io.ByteArrayOutputStream

class ViewModelAddDetails(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoAddPersonalDetails>(mApplication, isToShowErrors) {

    private val liveEventSubmit: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventGender: SingleLiveEvent<Int> = SingleLiveEvent()

    var observerStreamPic = ObservableField<ByteArrayOutputStream>()
    var observerPicUri = ObservableString("")


    var observerAboutMe: ObservableString = ObservableString("")
    var observerErrorAboutMe = ObservableInt()
    var observerErrorEnabledAboutMe = ObservableBoolean(false)

    var observerAddress: ObservableString = ObservableString("")
    var observerErrorAddress = ObservableInt()
    var observerErrorEnabledAddress = ObservableBoolean(false)

    var observerApt: ObservableString = ObservableString("")
    var observerErrorApt = ObservableInt()
    var observerErrorEnabledApt = ObservableBoolean(false)


    fun getLiveEventSubmit(): SingleLiveEvent<Boolean> {
        return liveEventSubmit
    }

    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }

    fun getLiveEventGender(): SingleLiveEvent<Int> {
        return liveEventGender
    }

    fun clickSubmit() {

        val validateAboutMe = validateAboutMe()
        val validateAddress = validateAddress()
        val validateapt = validateApt()

        liveEventSubmit.value =
            validateAboutMe && validateAddress && validateapt
    }

    fun clickBackPress() {
        liveEventBackPress.value = true
    }

    fun clickMale() {
        liveEventGender.value = 0
    }


    fun clickFemale() {
        liveEventGender.value = 1
    }

    private fun validateAboutMe(): Boolean {

        return if (TextUtils.isEmpty(observerAboutMe.getTrimmed())) {
            observerErrorEnabledAboutMe.set(true)
            observerErrorAboutMe.set(R.string.error_blank_name)
            false
        } else {
            observerErrorEnabledAboutMe.set(false)
            true
        }
    }

    private fun validateAddress(): Boolean {

        return if (TextUtils.isEmpty(observerAddress.getTrimmed())) {
            observerErrorEnabledAddress.set(true)
            observerErrorAddress.set(R.string.error_blank_address)
            false
        } else {
            observerErrorEnabledAddress.set(false)
            true
        }
    }

    private fun validateApt(): Boolean {

        return if (TextUtils.isEmpty(observerApt.getTrimmed())) {
            observerErrorEnabledApt.set(true)
            observerErrorApt.set(R.string.error_blank_apt)
            false
        } else {
            observerErrorEnabledAddress.set(false)
            true
        }
    }

    override fun networkCallData() {
        TODO("Not yet implemented")
    }

    override fun sendResponseBodyData(data: PojoAddPersonalDetails?) {
        TODO("Not yet implemented")
    }
}