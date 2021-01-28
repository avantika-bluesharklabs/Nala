package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoMassageCharges:PojoCommonResponse() {

    @SerializedName("txt_price")
    @Expose
    var txt_price: String? = null

    @SerializedName("txt_time")
    @Expose
    var txt_time: String? = null

    @SerializedName("txt_doller")
    @Expose
    var txt_doller: String? = null

    @SerializedName("img_left_arrow")
    @Expose
    var img_left_arrow: Int? = null

    @SerializedName("img_right_arrow")
    @Expose
    var img_right_arrow: Int? = null


}