package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoHomeDeatils

interface OnClickHomeDetails {

    fun onClickHomeDetailsItem(view: View?, layoutPosition: Int, data: PojoHomeDeatils)
}