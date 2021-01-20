package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoHome

interface OnClickHome {

    fun onClickHomeItem(view: View?, layoutPosition: Int, data: PojoHome)
}