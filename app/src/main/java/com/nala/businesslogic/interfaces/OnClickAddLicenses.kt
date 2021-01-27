package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoAddLicenses
import com.nala.businesslogic.pojo.PojoMassageCharges

interface OnClickAddLicenses {
    fun onClickAddLicensesItem(view: View?, layoutPosition: Int, data: PojoAddLicenses)

}