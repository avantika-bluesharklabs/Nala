package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoHomeItemList

interface OnClickHomeList {

    fun onClickHomeListItem(view: View?, layoutPosition: Int, data: PojoHomeItemList.Data)

}