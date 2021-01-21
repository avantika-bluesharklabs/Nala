package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoReview:PojoCommonResponse() {

    @SerializedName("txt_name")
    @Expose
    var txt_name: String? = null

    @SerializedName("txt_msg")
    @Expose
    var txt_msg: String? = null

   /* @SerializedName("txt_post_time")
    @Expose
    var txt_post_time: String? = null*/




}