package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoHome : PojoCommonResponse() {


    @SerializedName("txt_user_name")
    @Expose
    var txt_user_name: String? = null


}