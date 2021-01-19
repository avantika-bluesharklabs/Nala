package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoSchedualeAppoinment

interface OnClickSchedualeAppoinment {

    fun onClickSchedualeAppoinmentItem(view: View?, layoutPosition: Int, data: PojoSchedualeAppoinment)
}