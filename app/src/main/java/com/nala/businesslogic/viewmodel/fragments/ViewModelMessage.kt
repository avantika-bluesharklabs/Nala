package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoMessage
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelMessage (myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoMessage>(myApplication,false,false,
        0, RecyclerView.VERTICAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoMessage> = SingleLiveEvent()

    init {

        var pojo = PojoMessage()
        pojo.txt_name = "Robert Mark"
        pojo.txt_status = "Thank you!"
        pojo.txt_time = "1d ago"

        observerContent.add(pojo)

        pojo =PojoMessage()
        pojo.txt_name = "Virat kohli"
        pojo.txt_status = "Nice"
        pojo.txt_time = "1Week ago"

        observerContent.add(pojo)

        pojo = PojoMessage()
        pojo.txt_name = "Rishabh Pant"
        pojo.txt_status = "Your Service is Awesome"
        pojo.txt_time = "3d ago"

        observerContent.add(pojo)

        pojo = PojoMessage()
        pojo.txt_name = "Ajinkya rahane"
        pojo.txt_status = "Great Idea!!!"
        pojo.txt_time = "1year ago"

        observerContent.add(pojo)


    }



    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoMessage> {
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