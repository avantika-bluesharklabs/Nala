package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.interactors.SingleLiveEvent
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoReview
import com.nala.businesslogic.pojo.PojoSchedualeAppoinmentType
import com.nala.businesslogic.pojo.PojoTechnique
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelReview(myApplication: MyApplication, isToShowErrors: Boolean) :
    ViewModelRecyclerView<PojoCommonResponse, PojoReview>(myApplication,false,false,
        0, RecyclerView.VERTICAL){


    private val liveEvent: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val liveEventSuccess: SingleLiveEvent<PojoReview> = SingleLiveEvent()

    init {

        var pojoreview =PojoReview()
        pojoreview.txt_name = "Jason M"
        pojoreview.txt_msg = "Lorem ipsum dolor sit amet"
      //  pojoreview.txt_post_time = "2d Ago"
        observerContent.add(pojoreview)

        pojoreview =PojoReview()
        pojoreview.txt_name = "Micheal Jacson"
        pojoreview.txt_msg = "Lorem ipsum dolor sit amet"
      //  pojoreview.txt_post_time = "2d Ago"
        observerContent.add(pojoreview)

        pojoreview =PojoReview()
        pojoreview.txt_name = "Kevin Lord"
        pojoreview.txt_msg = "Lorem ipsum dolor sit amet"
      //  pojoreview.txt_post_time = "2d Ago"
        observerContent.add(pojoreview)

        pojoreview =PojoReview()
        pojoreview.txt_name = "Billl Clinton"
        pojoreview.txt_msg = "Lorem ipsum dolor sit amet"
      //  pojoreview.txt_post_time = "2d Ago"
        observerContent.add(pojoreview)


    }



    fun getLiveEvent(): SingleLiveEvent<Boolean> {
        return liveEvent
    }

    fun getLiveEventSuccess(): SingleLiveEvent<PojoReview> {
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