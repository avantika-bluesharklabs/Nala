package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoSchedualeAppoinment
import com.nala.businesslogic.pojo.PojoTechnique
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelTechnique (myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoTechnique>(myApplication,false,true,
        2, RecyclerView.VERTICAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoTechnique> = SingleLiveEvent()

    init {

        var pojo = PojoTechnique()
        pojo.txt_status = "Deep Tissue"
        observerContent.add(pojo)


        pojo = PojoTechnique()
        pojo.txt_status = "Swedesi"
        observerContent.add(pojo)


        pojo = PojoTechnique()
        pojo.txt_status = "Sports"
        observerContent.add(pojo)


        pojo = PojoTechnique()
        pojo.txt_status = "Reflexology"
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

    fun getLiveEventSuccess(): SingleLiveEvent<PojoTechnique> {
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