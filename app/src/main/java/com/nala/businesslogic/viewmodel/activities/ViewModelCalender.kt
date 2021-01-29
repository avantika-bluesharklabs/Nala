package com.nala.businesslogic.viewmodel.activities

import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.view.MyApplication

class ViewModelCalender(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoCommonResponse>(mApplication, isToShowErrors) {


    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventClearSchedule: SingleLiveEvent<Boolean> = SingleLiveEvent()


    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }

    fun getLiveEventClearSchedule(): SingleLiveEvent<Boolean> {
        return liveEventClearSchedule
    }


    fun clickClearSchedule() {
        liveEventClearSchedule.value = true
    }


    override fun networkCallData() {


    }

    override fun sendResponseBodyData(data: PojoCommonResponse?) {
    }

}