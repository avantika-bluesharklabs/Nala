package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoAddLicenses :PojoCommonResponse(){

    @SerializedName("txt_document_name")
    @Expose
    var txt_document_name: String? = null

    var data:ArrayList<String>? = null




}