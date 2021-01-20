package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoSchedualeAppoinment
import com.nala.businesslogic.pojo.PojoSchedualeAppoinmentType

interface OnClickSchedualeAppoinmentType {

    fun onClickSchedualeAppoinmentTypeItem(view: View?, layoutPosition: Int, data: PojoSchedualeAppoinmentType)

}