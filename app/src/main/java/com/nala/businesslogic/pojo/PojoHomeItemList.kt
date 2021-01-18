package com.nala.businesslogic.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PojoHomeItemList : PojoCommonResponse() {

    @SerializedName("data")
    @Expose
    var data: ArrayList<Data> = ArrayList()

    inner class Data {

        @SerializedName("id")
        @Expose
        val id: String? = null

        @SerializedName("name")
        @Expose
        val name: String? = null

        @SerializedName("experience")
        @Expose
        val experience: String? = null

        @SerializedName("service_location")
        @Expose
        val service_location: String? = null

        @SerializedName("techniques")
        @Expose
        val techniques: String? = null

        @SerializedName("rating")
        @Expose
        val rating: String? = null

        @SerializedName("is_favorite")
        @Expose
        val isFavorite: String? = null

        @SerializedName("profile_pic")
        @Expose
        val profilePic: String? = null

    }

}