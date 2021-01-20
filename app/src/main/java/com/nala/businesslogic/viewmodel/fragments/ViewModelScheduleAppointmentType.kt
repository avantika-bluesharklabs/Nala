package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoSchedualeAppoinment
import com.nala.businesslogic.pojo.PojoSchedualeAppoinmentType
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelScheduleAppointmentType(myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoSchedualeAppoinmentType>(myApplication,false,false,
        0, RecyclerView.HORIZONTAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoSchedualeAppoinmentType> = SingleLiveEvent()

    init {

        var pojo = PojoSchedualeAppoinmentType()
        pojo.txt_type = "In Studio"

        observerContent.add(pojo)

        pojo = PojoSchedualeAppoinmentType()
        pojo.txt_type = "Mobile Service With Table"

        observerContent.add(pojo)

        pojo = PojoSchedualeAppoinmentType()
        pojo.txt_type = "In Dount"

        observerContent.add(pojo)

        pojo = PojoSchedualeAppoinmentType()
        pojo.txt_type = "Services"


        observerContent.add(pojo)


    }



    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoSchedualeAppoinmentType> {
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