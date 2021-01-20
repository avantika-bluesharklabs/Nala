package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoHome
import com.nala.businesslogic.pojo.PojoMyBooking

interface OnClickMyBooking {

    fun onClickMyBookingItem(view: View?, layoutPosition: Int, data: PojoMyBooking)
}