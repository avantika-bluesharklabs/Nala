package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoHome
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelHomeMap(myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoHome>(myApplication,false,false,
        0, RecyclerView.HORIZONTAL){


    var user_lat = 0.000
    var user_long = 0.000
    var city_name = ""

    var observerCityName: ObservableString = ObservableString("")


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoHome> = SingleLiveEvent()

    init {

        var pojohome = PojoHome()
        pojohome.txt_user_name = "Jessica Marcel"

        observerContent.add(pojohome)

        pojohome = PojoHome()
        pojohome.txt_user_name = "James Mark"

        observerContent.add(pojohome)

        pojohome = PojoHome()
        pojohome.txt_user_name = "Cellina Mark"

        observerContent.add(pojohome)

        pojohome = PojoHome()
        pojohome.txt_user_name ="Jorden King"

        observerContent.add(pojohome)


    }



    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoHome> {
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