package com.nala.businesslogic.pojo

import com.google.gson.annotations.SerializedName


open class PojoCommonResponse {

    @SerializedName("status")
    private var status: Boolean? = null

    @SerializedName("message")
    private var message: String? = null

    @SerializedName("offset")
    private var offset: Int? = 0

    @SerializedName("total_count")
    private var totalCount: Int? = 0

    fun getStatus(): Boolean? {
        return status
    }

    fun setStatus(success: Boolean?) {
        this.status = success
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getTotalCount(): Int? {
        return totalCount
    }

    fun setTotalCount(totalCount: Int?) {
        this.totalCount = totalCount
    }

    fun getOffset(): Int? {
        return offset
    }

    fun setOffset(offset: Int?) {
        this.offset = offset
    }

}