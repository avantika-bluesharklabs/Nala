package com.nala.businesslogic.interactors

import android.text.TextUtils
import androidx.databinding.ObservableField

/**
 * Created by Avantika Gadhiya on 3/31/2020.
 */
class ObservableString : ObservableField<String> {

    constructor(value: String?) {
        set(value)
    }

    constructor() {}

    override fun get(): String {
        return if (super.get() == null) "" else (super.get()!!)
    }

    fun getTrimmed(): String {
        val stringData = get()
        return if (!TextUtils.isEmpty(stringData) && !TextUtils.isEmpty(stringData.trim())) stringData.trim() else ("")
    }

    fun getTrimmedLength(): Int {
        val trimmedData = getTrimmed()
        return if (!TextUtils.isEmpty(trimmedData)) trimmedData.length else (0)
    }

    fun isEmpty(): Boolean {
        val stringData = get()
        return (TextUtils.isEmpty(stringData) || TextUtils.isEmpty(stringData.trim()))
    }

}