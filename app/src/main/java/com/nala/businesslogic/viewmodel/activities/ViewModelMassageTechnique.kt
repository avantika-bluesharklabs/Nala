package com.nala.businesslogic.viewmodel.activities

import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoMassageCharges
import com.nala.businesslogic.pojo.PojoMassageType
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelMassageTechnique(myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoMassageType>(myApplication,false,true,
        3, RecyclerView.VERTICAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoMassageType> = SingleLiveEvent()
    private val liveEventContinue: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()

    init {

        var pojo = PojoMassageType()
        pojo.txt_type = "Deep Tissue"
        observerContent.add(pojo)

        pojo =PojoMassageType()
        pojo.txt_type = "Swedish"
        observerContent.add(pojo)

        pojo =  PojoMassageType()
        pojo.txt_type = "Sports"
        observerContent.add(pojo)

        pojo =  PojoMassageType()
        pojo.txt_type = "Reflexology"
        observerContent.add(pojo)

        pojo =  PojoMassageType()
        pojo.txt_type = "Thai"
        observerContent.add(pojo)

        pojo =  PojoMassageType()
        pojo.txt_type = "Hot stone"
        observerContent.add(pojo)

        pojo =  PojoMassageType()
        pojo.txt_type = "Aror"
        observerContent.add(pojo)

        pojo =  PojoMassageType()
        pojo.txt_type = "Trigger point"
        observerContent.add(pojo)

        pojo =  PojoMassageType()
        pojo.txt_type = "Shiatsu"
        observerContent.add(pojo)

        pojo =  PojoMassageType()
        pojo.txt_type = "Chair massage"
        observerContent.add(pojo)

    }






    fun getLiveEventBackPress(): SingleLiveEvent<Boolean> {
        return liveEventBackPress
    }

    fun getLiveEventContinue(): SingleLiveEvent<Boolean> {
        return liveEventContinue
    }


    fun clickBackPress() {
        liveEventBackPress.value = true
    }

    fun clickContinue() {
        liveEventContinue.value = true
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