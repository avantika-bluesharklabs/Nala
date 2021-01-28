package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoMyBooking:PojoCommonResponse() {

    @SerializedName("txt_user_name")
    @Expose
    var txt_user_name: String? = null

    @SerializedName("txt_status")
    @Expose
    var txt_status: String? = null

    @SerializedName("txt_massagee")
    @Expose
    var txt_massagee: String? = null


}