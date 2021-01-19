package com.nala.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickTechnique
import com.nala.businesslogic.pojo.PojoTechnique
import com.nala.businesslogic.viewmodel.activities.ViewModelTechnique
import com.nala.databinding.ActivityServiceProBinding

class ActivityServicePro : ActivityBase(),OnClickTechnique {

    lateinit var mViewModelTechnique: ViewModelTechnique
    private lateinit var mBinding: ActivityServiceProBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSemiLightGray);
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service_pro)
        mViewModelTechnique = ViewModelTechnique(mApplication, true)
        mBinding.vmTechnique = mViewModelTechnique
        mBinding.onContentClickListener = this


        mBinding.btnBookNow.setOnClickListener {  startActivity(Intent(mContext, ActivityScheduleAppointment::class.java)) }


    }



    override fun onClickTechniqueItem(view: View?, layoutPosition: Int, data: PojoTechnique) {

    }
}