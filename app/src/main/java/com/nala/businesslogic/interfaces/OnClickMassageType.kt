package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoMassageCharges
import com.nala.businesslogic.pojo.PojoMassageType

interface OnClickMassageType {

    fun onClickMassageTypeItem(view: View?, layoutPosition: Int, data: PojoMassageType)
}