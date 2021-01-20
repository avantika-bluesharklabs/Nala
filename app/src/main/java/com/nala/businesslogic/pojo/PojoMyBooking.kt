package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoMyBooking:PojoCommonResponse() {

    @SerializedName("txt_name")
    @Expose
    var txt_name: String? = null

    @SerializedName("txt_status")
    @Expose
    var txt_status: String? = null


}