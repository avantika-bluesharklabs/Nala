package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoNotisfication
import com.nala.businesslogic.pojo.PojoSchedualeAppoinment

interface OnClickNotification {
    fun onClickNotificationItem(view: View?, layoutPosition: Int, data: PojoNotisfication)

}