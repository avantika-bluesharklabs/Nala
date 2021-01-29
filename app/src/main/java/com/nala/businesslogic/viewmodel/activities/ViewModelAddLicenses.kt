package com.nala.businesslogic.viewmodel.activities

import android.view.View
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoAddLicenses
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoMassageCharges
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelAddLicenses(myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoAddLicenses>(
        myApplication, false, false,
        0, RecyclerView.VERTICAL
    ) {


    var observerStartTime1: ObservableString = ObservableString("")
    var observerStartTime1Position : ObservableInt = ObservableInt(0)


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoAddLicenses> = SingleLiveEvent()
    private val liveEventContinue: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventBackPress: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSessionTopic: SingleLiveEvent<String> = SingleLiveEvent()





    init {

        var arrayData = arrayOf("Vadodara", "Ahmedabad", "Surat", "Mumbai")


        var pojo = PojoAddLicenses()

        pojo.txt_document_name = "Licence_1.jpg"
        pojo.data?.addAll(arrayData)
        observerContent.add(pojo)

        pojo = PojoAddLicenses()
        pojo.txt_document_name = "Licence_1.jpg"
        pojo.data?.addAll(arrayData)
        observerContent.add(pojo)

        pojo = PojoAddLicenses()

        pojo.txt_document_name = "Licence_1.jpg"
        pojo.data?.addAll(arrayData)
        observerContent.add(pojo)

        pojo = PojoAddLicenses()
        pojo.txt_document_name = "Licence_1.jpg"
        pojo.data?.addAll(arrayData)
        observerContent.add(pojo)


    }


    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoAddLicenses> {
        return liveEventSuccess
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

    fun clickContinue() {
        liveEventContinue.value = true
    }
  /*  fun onItemSelectedStartTime1Selection(view: View, position: Int) {
        if (position > 0) {
            liveEventSessionTopic.setValue(arrayData.get(position-1))
            observerStartTime1 = ObservableString(arrayData.get(position-1))
        }
    }*/

    override fun refreshListUpdate() {

    }

    override fun networkCallList() {

    }

    override fun offlineDataList() {

    }

    override fun sendResponseBodyList(list: PojoCommonResponse?) {

    }


}