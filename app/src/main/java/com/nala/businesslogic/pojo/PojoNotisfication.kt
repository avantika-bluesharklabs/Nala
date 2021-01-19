package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoNotisfication:PojoCommonResponse() {

    @SerializedName("txt_date")
    @Expose
    var txt_date: String? = null

    @SerializedName("txt_name")
    @Expose
    var txt_name: String? = null

    @SerializedName("txt_status")
    @Expose
    var txt_status: String? = null


    @SerializedName("txt_time")
    @Expose
    var txt_time: String? = null





}