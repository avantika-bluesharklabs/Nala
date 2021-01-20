package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoHome
import com.nala.businesslogic.pojo.PojoNotisfication
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelNotisfication(myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoNotisfication>(myApplication,false,false,
        0, RecyclerView.VERTICAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoNotisfication> = SingleLiveEvent()

    init {

        var pojo = PojoNotisfication()
        pojo.txt_name = "Lorem ipsum dolor sit amet"
        pojo.txt_status = "Lorem ipsum dolor sit amet"
        pojo.txt_date = "28 November,2019"
        pojo.txt_time = "2 October,2020, 11:30am"

        observerContent.add(pojo)

        pojo = PojoNotisfication()
        pojo.txt_name = "Lorem ipsum dolor sit amet"
        pojo.txt_status = "Lorem ipsum dolor sit amet"
        pojo.txt_date = "28 November,2019"
        pojo.txt_time = "2 October,2020, 11:30am"

        observerContent.add(pojo)

        pojo = PojoNotisfication()
        pojo.txt_name = "Lorem ipsum dolor sit amet"
        pojo.txt_status = "Lorem ipsum dolor sit amet"
        pojo.txt_date = "28 November,2019"
        pojo.txt_time = "2 October,2020, 11:30am"

        observerContent.add(pojo)

        pojo = PojoNotisfication()
        pojo.txt_name = "Lorem ipsum dolor sit amet"
        pojo.txt_status = "Lorem ipsum dolor sit amet"
        pojo.txt_date = "28 November,2019"
        pojo.txt_time = "2 October,2020, 11:30am"

        observerContent.add(pojo)


    }



    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoNotisfication> {
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