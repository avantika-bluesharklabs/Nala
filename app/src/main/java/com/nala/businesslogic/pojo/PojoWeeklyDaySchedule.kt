package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoWeeklyDaySchedule:PojoCommonResponse() {

    @SerializedName("txt_day")
    @Expose
    var txt_day: String? = null

    var isSelected: Boolean? = false

}