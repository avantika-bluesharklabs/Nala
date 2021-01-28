package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoWeeklyDaySchedule
import com.nala.businesslogic.pojo.PojoWeeklyTimeSchedule

interface OnClickWeeklyTimeSchedule {

    fun onClickWeeklyTimeScheduleItem(view: View?, layoutPosition: Int, data: PojoWeeklyTimeSchedule)
}