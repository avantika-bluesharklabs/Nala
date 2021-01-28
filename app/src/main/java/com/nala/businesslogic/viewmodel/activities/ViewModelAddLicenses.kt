package com.nala.businesslogic.viewmodel.activities

import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoAddLicenses
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoMassageCharges
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelAddLicenses (myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoAddLicenses>(myApplication,false,false,
        0, RecyclerView.VERTICAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoAddLicenses> = SingleLiveEvent()

    init {

        var pojo = PojoAddLicenses()

        pojo.txt_document_name = "Licence_1.jpg"


        observerContent.add(pojo)

        pojo = PojoAddLicenses()

        pojo.txt_document_name = "Licence_1.jpg"

        observerContent.add(pojo)

        pojo = PojoAddLicenses()

        pojo.txt_document_name = "Licence_1.jpg"

        observerContent.add(pojo)

        pojo = PojoAddLicenses()

        pojo.txt_document_name = "Licence_1.jpg"

        observerContent.add(pojo)


    }



    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoAddLicenses> {
        return liveEventSuccess
    }

    override fun refreshListUpdate() {

    }

    override fun networkCallList() {

    }

    override fun offlineDataList() {

    }

    override fun sendResponseBodyList(list: PojoCommonResponse?) {

    }



}