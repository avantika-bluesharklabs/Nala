package com.nala.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickHomeDetails
import com.nala.businesslogic.pojo.PojoHomeDeatils
import com.nala.businesslogic.viewmodel.activities.ViewModelHomeDetails
import com.nala.databinding.ActivityHomeDetailsBinding

class ActivityHomeDetails : ActivityBase(),OnClickHomeDetails {

    lateinit var mViewModelHomeDetails: ViewModelHomeDetails
    private lateinit var mBinding: ActivityHomeDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSemiLightGray);
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_details)
        mViewModelHomeDetails = ViewModelHomeDetails(mApplication, true)
        mBinding.vmHomeDetails = mViewModelHomeDetails


        mBinding.btnBookNow.setOnClickListener {  startActivity(Intent(mContext, ActivityScheduleAppointment::class.java)) }


    }

    override fun onClickHomeDetailsItem(view: View?, layoutPosition: Int, data: PojoHomeDeatils) {

    }
}