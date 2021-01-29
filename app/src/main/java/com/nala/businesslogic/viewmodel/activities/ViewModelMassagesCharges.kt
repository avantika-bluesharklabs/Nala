package com.nala.businesslogic.viewmodel.activities

import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoMassageCharges
import com.nala.businesslogic.pojo.PojoMyBooking
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelMassagesCharges (myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoMassageCharges>(myApplication,false,false,
        0, RecyclerView.VERTICAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventContinue: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoMassageCharges> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {

        var pojo = PojoMassageCharges()
        pojo.txt_price = "25"
        pojo.txt_time = "/30 min"
        pojo.txt_doller = "$"
        pojo.img_left_arrow = R.drawable.img_home_dummy
        pojo.img_right_arrow = R.drawable.img_home_dummy

        observerContent.add(pojo)

        pojo = PojoMassageCharges()
        pojo.txt_price = "40"
        pojo.txt_time = "/60 min"
        pojo.txt_doller = "$"
        pojo.img_left_arrow = R.drawable.img_home_dummy
        pojo.img_right_arrow = R.drawable.img_home_dummy

        observerContent.add(pojo)

        pojo =  PojoMassageCharges()
        pojo.txt_price = "55"
        pojo.txt_time = "/90 min"
        pojo.txt_doller = "$"
        pojo.img_left_arrow = R.drawable.img_home_dummy
        pojo.img_right_arrow = R.drawable.img_home_dummy

        observerContent.add(pojo)

        pojo =  PojoMassageCharges()
        pojo.txt_price = "75"
        pojo.txt_time ="/120 min"
        pojo.txt_doller = "$"
        pojo.img_left_arrow = R.drawable.img_home_dummy
        pojo.img_right_arrow = R.drawable.img_home_dummy

        observerContent.add(pojo)


    }



    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }

    fun clickBackPress() {
        liveEventBackPress.value = true
    }

    fun getLiveEventContinue(): SingleLiveEvent<Boolean> {
        return liveEventContinue
    }

    fun clickContinue(){
        liveEventContinue.value = true
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoMassageCharges> {
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