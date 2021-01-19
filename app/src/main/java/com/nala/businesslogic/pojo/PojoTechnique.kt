package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoTechnique:PojoCommonResponse() {

    @SerializedName("txt_status")
    @Expose
    var txt_status: String? = null
}