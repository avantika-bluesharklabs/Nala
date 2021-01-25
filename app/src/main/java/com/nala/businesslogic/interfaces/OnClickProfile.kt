package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.pojo.PojoNotisfication

interface OnClickProfile {

    fun onClickProfileItem(view: View?, layoutPosition: Int, data: PojoCommonResponse)
}