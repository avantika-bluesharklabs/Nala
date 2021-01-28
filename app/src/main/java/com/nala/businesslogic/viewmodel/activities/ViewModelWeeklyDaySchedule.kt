package com.nala.businesslogic.viewmodel.activities

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoWeeklyDaySchedule
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelWeeklyDaySchedule(myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoWeeklyDaySchedule>(
        myApplication, false, false,
        0, RecyclerView.HORIZONTAL
    ) {


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoWeeklyDaySchedule> = SingleLiveEvent()

    init {

        var pojo = PojoWeeklyDaySchedule()
        pojo.txt_day = "Su"
        observerContent.add(pojo)

        pojo = PojoWeeklyDaySchedule()
        pojo.txt_day = "Mo"
        observerContent.add(pojo)

        pojo = PojoWeeklyDaySchedule()
        pojo.txt_day = "Tu"
        observerContent.add(pojo)

        pojo = PojoWeeklyDaySchedule()
        pojo.txt_day = "We"
        observerContent.add(pojo)

        pojo = PojoWeeklyDaySchedule()
        pojo.txt_day = "Th"
        observerContent.add(pojo)

        pojo = PojoWeeklyDaySchedule()
        pojo.txt_day = "Fr"
        observerContent.add(pojo)

        pojo = PojoWeeklyDaySchedule()
        pojo.txt_day = "Sa"
        observerContent.add(pojo)




    }


    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoWeeklyDaySchedule> {
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