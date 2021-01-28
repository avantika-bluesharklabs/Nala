package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoWeeklyTimeSchedule : PojoCommonResponse() {

    @SerializedName("txt_time_slot")
    @Expose
    var txt_time_slot: String? = null


}