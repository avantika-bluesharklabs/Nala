package com.nala.businesslogic.viewmodel.activities

import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.view.MyApplication

class ViewModelMain(
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoCommonResponse>(mApplication, isToShowErrors) {


    override fun networkCallData() {
    }

    override fun sendResponseBodyData(data: PojoCommonResponse?) {
    }
}