package com.nala.businesslogic.viewmodel.fragments

import androidx.recyclerview.widget.RecyclerView
import com.nala.businesslogic.pojo.PojoHomeItemList
import com.nala.businesslogic.viewmodel.ViewModelRecyclerView
import com.nala.view.MyApplication

class ViewModelHomeList(myApplication: MyApplication) :
ViewModelRecyclerView<PojoHomeItemList, PojoHomeItemList.Data>(myApplication,false,false,2,
RecyclerView.VERTICAL) {


    override fun refreshListUpdate() {
    }
    override fun offlineDataList() {
    }

    override fun networkCallList() {
        TODO("Not yet implemented")
    }

    override fun sendResponseBodyList(list: PojoHomeItemList?) {
        TODO("Not yet implemented")
    }

}