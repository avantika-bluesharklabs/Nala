package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoMessage

interface OnClickMessage {

    fun onClickMessageItem(view: View?, layoutPosition: Int, data: PojoMessage)
}