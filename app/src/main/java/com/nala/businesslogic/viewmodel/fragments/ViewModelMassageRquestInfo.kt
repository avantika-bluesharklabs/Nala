package com.nala.businesslogic.viewmodel.fragments

import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.view.MyApplication

class ViewModelMassageRquestInfo(mApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelCommon<PojoCommonResponse>(mApplication, isToShowErrors) {


    private val eventClickBack: SingleLiveEvent<Boolean> = SingleLiveEvent()
    fun getEventBack(): SingleLiveEvent<Boolean> {
        return eventClickBack
    }

    fun clickBack() {
        eventClickBack.value = true
    }


    override fun networkCallData() {
    }

    override fun sendResponseBodyData(data: PojoCommonResponse?) {
    }
}