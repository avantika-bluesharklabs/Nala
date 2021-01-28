package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoHome
import com.nala.businesslogic.pojo.PojoMyBooking
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelMyBookings(myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoMyBooking>(myApplication,false,false,
        0, RecyclerView.VERTICAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoMyBooking> = SingleLiveEvent()

    init {

        var pojoMyBooking = PojoMyBooking()
        pojoMyBooking.txt_user_name = "Rahul Dravid"
        pojoMyBooking.txt_status = "Pending"
        pojoMyBooking.txt_massagee = "Deep tissue,Swedish"

        observerContent.add(pojoMyBooking)

        pojoMyBooking = PojoMyBooking()
        pojoMyBooking.txt_user_name = "Sachin Tendulkar"
        pojoMyBooking.txt_status = "Accepted"
        pojoMyBooking.txt_massagee = "Deep tissue,Swedish"

        observerContent.add(pojoMyBooking)

        pojoMyBooking =  PojoMyBooking()
        pojoMyBooking.txt_user_name = "Hanuma  Vihari"
        pojoMyBooking.txt_status = "Rejected"
        pojoMyBooking.txt_massagee = "Deep tissue,Swedish"

        observerContent.add(pojoMyBooking)

        pojoMyBooking =  PojoMyBooking()
        pojoMyBooking.txt_user_name = "Pant Rishabh"
        pojoMyBooking.txt_status ="Pending"
        pojoMyBooking.txt_massagee = "Deep tissue,Swedish"
        observerContent.add(pojoMyBooking)


    }



    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoMyBooking> {
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