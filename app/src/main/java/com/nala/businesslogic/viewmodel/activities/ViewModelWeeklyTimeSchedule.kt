package com.nala.businesslogic.viewmodel.activities

import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoWeeklyDaySchedule
import com.nala.businesslogic.pojo.PojoWeeklyTimeSchedule
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelWeeklyTimeSchedule (myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoWeeklyTimeSchedule>(myApplication,false,true,
        2, RecyclerView.VERTICAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoWeeklyTimeSchedule> = SingleLiveEvent()

    init {

        var pojo = PojoWeeklyTimeSchedule()
        pojo.txt_time_slot =" 1 Jan,2021 to 7 Jan,2021"
        observerContent.add(pojo)

         pojo = PojoWeeklyTimeSchedule()
        pojo.txt_time_slot =" 1 Jan,2021 to 7 Jan,2021"
        observerContent.add(pojo)

         pojo = PojoWeeklyTimeSchedule()
        pojo.txt_time_slot =" 1 Jan,2021 to 7 Jan,2021"
        observerContent.add(pojo)

         pojo = PojoWeeklyTimeSchedule()
        pojo.txt_time_slot =" 1 Jan,2021 to 7 Jan,2021"
        observerContent.add(pojo)




    }



    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoWeeklyTimeSchedule> {
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