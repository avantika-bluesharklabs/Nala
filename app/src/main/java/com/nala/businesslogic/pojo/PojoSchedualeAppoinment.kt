package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoSchedualeAppoinment:PojoCommonResponse() {

    @SerializedName("txt_time")
    @Expose
    var txt_time: String? = null
}