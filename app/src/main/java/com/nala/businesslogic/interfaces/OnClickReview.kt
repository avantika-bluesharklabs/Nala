package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoNotisfication
import com.nala.businesslogic.pojo.PojoReview

interface OnClickReview {

    fun onClickReviewItem(view: View?, layoutPosition: Int, data: PojoReview)
}