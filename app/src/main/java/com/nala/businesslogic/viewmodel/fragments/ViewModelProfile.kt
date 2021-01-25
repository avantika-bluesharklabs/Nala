package com.nala.businesslogic.viewmodel.fragments

import androidx.databinding.ObservableField
import com.bumptech.glide.request.RequestOptions
import com.nala.R
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.view.MyApplication
import java.io.ByteArrayOutputStream

class ViewModelProfile (
    mApplication: MyApplication,
    isToShowErrors: Boolean
) : ViewModelCommon<PojoCommonResponse>(mApplication, isToShowErrors) {

    private val liveEventImage: SingleLiveEvent<Boolean> = SingleLiveEvent()




    var observerStreamPic = ObservableField<ByteArrayOutputStream>()
    var observerPicUri = ObservableString("")
    var observerPic = ObservableString("")



    override fun networkCallData() {


    }

    override fun sendResponseBodyData(data: PojoCommonResponse?) {
    }

    fun clickImage() {
        liveEventImage.value = true
    }
}