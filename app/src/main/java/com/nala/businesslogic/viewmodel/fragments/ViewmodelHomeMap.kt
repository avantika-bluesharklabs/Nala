package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoMyBooking
import com.nala.businesslogic.pojo.PojoHomeMap
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewmodelHomeMap(myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoHomeMap>(myApplication,false,false,
        0, RecyclerView.HORIZONTAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoHomeMap> = SingleLiveEvent()

    init {

     /*   var pojoMyBooking = PojoMyBooking()
        pojoMyBooking.txt_name = "Rahul Dravid"
        pojoMyBooking.txt_status = "Pending"

        observerContent.add(pojoMyBooking)

        pojoMyBooking = PojoMyBooking()
        pojoMyBooking.txt_name = "Sachin Tendulkar"
        pojoMyBooking.txt_status = "Accepted"

        observerContent.add(pojoMyBooking)

        pojoMyBooking =  PojoMyBooking()
        pojoMyBooking.txt_name = "Hanuma  Vihari"
        pojoMyBooking.txt_status = "Rejected"

        observerContent.add(pojoMyBooking)

        pojoMyBooking =  PojoMyBooking()
        pojoMyBooking.txt_name = "Pant Rishabh"
        pojoMyBooking.txt_status ="Pending"

        observerContent.add(pojoMyBooking)*/


    }



    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoHomeMap> {
        return liveEventSuccess
    }

    override fun refreshListUpdate() {
        TODO("Not yet implemented")
    }

    override fun networkCallList() {
        TODO("Not yet implemented")
    }

    override fun offlineDataList() {
        TODO("Not yet implemented")
    }

    override fun sendResponseBodyList(list: PojoCommonResponse?) {
        TODO("Not yet implemented")
    }



}