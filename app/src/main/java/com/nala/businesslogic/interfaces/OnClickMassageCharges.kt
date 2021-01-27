package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoHomeItemList
import com.nala.businesslogic.pojo.PojoMassageCharges

interface OnClickMassageCharges {

    fun onClickMassageChargesItem(view: View?, layoutPosition: Int, data: PojoMassageCharges)
}