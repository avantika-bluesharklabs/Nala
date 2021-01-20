package com.nala.businesslogic.viewmodel.fragments

import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.view.MyApplication

class ViewModelChat(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoCommonResponse>(mApplication, isToShowErrors) {


    override fun networkCallData() {
    }

    override fun sendResponseBodyData(data: PojoCommonResponse?) {
    }
}