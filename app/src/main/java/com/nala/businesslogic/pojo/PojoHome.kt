package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoHome: PojoCommonResponse()  {



    @SerializedName("txt_name")
    @Expose
    var txt_name: String? = null



}