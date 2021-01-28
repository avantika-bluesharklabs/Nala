package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoWeeklyDaySchedule

interface OnClickWeeklyDaySchedule {

    fun onClickWeeklyDayScheduleItem(view: View?, layoutPosition: Int, data: PojoWeeklyDaySchedule)
}