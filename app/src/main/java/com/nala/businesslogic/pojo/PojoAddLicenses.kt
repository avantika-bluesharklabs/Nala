package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoAddLicenses {

    @SerializedName("txt_document_name")
    @Expose
    var txt_document_name: String? = null

    @SerializedName("txt_city_name")
    @Expose
    var txt_city_name: String? = null


}