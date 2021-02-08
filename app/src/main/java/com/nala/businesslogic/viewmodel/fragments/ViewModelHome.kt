package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoHome
import com.nala.businesslogic.viewmodel.ViewModelCommon
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelHome(myApplication: MyApplication,isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoHome>(myApplication,false,false,
        0,RecyclerView.VERTICAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoHome> = SingleLiveEvent()

    init {

        var pojohome = PojoHome()
        pojohome.txt_user_name = "Jessica Marcel"
        observerContent.add(pojohome)


        pojohome = PojoHome()
        pojohome.txt_user_name = "Cellina Mark"
        observerContent.add(pojohome)


        pojohome = PojoHome()
        pojohome.txt_user_name = "Petor Mark"
        observerContent.add(pojohome)


        pojohome = PojoHome()
        pojohome.txt_user_name ="Jessica lawrence"
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