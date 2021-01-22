package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoHome
import com.nala.businesslogic.pojo.PojoSchedualeAppoinment
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelScheduleAppointmentTime (myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoSchedualeAppoinment>(myApplication,false,false,
        0, RecyclerView.HORIZONTAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoSchedualeAppoinment> = SingleLiveEvent()

    init {

        var pojo = PojoSchedualeAppoinment()
        pojo.txt_time = "10:00 am"

        observerContent.add(pojo)

        pojo = PojoSchedualeAppoinment()
        pojo.txt_time = "10:30 am"

        observerContent.add(pojo)

        pojo = PojoSchedualeAppoinment()
        pojo.txt_time = "11:00 am"

        observerContent.add(pojo)

        pojo = PojoSchedualeAppoinment()
        pojo.txt_time = "11:30 am"


        observerContent.add(pojo)


    }

    private val eventClickBack: SingleLiveEvent<Boolean> = SingleLiveEvent()
    fun getEventBack(): SingleLiveEvent<Boolean> {
        return eventClickBack
    }

    fun clickBack() {
        eventClickBack.value = true
    }




    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoSchedualeAppoinment> {
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