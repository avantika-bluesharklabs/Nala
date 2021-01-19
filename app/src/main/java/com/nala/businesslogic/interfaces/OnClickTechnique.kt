package com.nala.businesslogic.interfaces

import android.view.View
import com.nala.businesslogic.pojo.PojoTechnique

interface OnClickTechnique {

    fun onClickTechniqueItem(view: View?, layoutPosition: Int, data: PojoTechnique)
}