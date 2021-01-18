package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoHomeDeatils
import com.nala.businesslogic.pojo.PojoHomeMap

interface OnClickHomeMap {

    fun onClickHomeMapItem(view: View?, layoutPosition: Int, data: PojoHomeMap)
}