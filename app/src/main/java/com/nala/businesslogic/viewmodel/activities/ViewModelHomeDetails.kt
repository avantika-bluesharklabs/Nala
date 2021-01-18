package com.nala.businesslogic.viewmodel.activities

import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.view.MyApplication

class ViewModelHomeDetails (mApplication: MyApplication,
                            isToShowErrors: Boolean
) :  ViewModelCommon<PojoCommonResponse>(mApplication, isToShowErrors){

    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoCommonResponse> = SingleLiveEvent()

    init {
        //  networkCallData()
    }

    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoCommonResponse> {
        return liveEventSuccess
    }


    override fun networkCallData() {

    }

    override fun sendResponseBodyData(data: PojoCommonResponse?) {

    }

}