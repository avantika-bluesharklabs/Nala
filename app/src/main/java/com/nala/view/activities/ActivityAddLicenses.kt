package com.nala.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickAddLicenses
import com.nala.businesslogic.pojo.PojoAddLicenses
import com.nala.businesslogic.viewmodel.activities.ViewModelAddLicenses
import com.nala.businesslogic.viewmodel.activities.ViewModelMassagesCharges
import com.nala.databinding.ActivityAddLicensesBinding
import com.nala.databinding.ActivityMassageChargesBinding

class ActivityAddLicenses : ActivityBase(),OnClickAddLicenses {

    private lateinit var mViewModelAddLicenses: ViewModelAddLicenses
    private lateinit var mBinding: ActivityAddLicensesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_licenses)
        mViewModelAddLicenses = ViewModelAddLicenses(mApplication, true)
        mBinding.vmAddLicenses = mViewModelAddLicenses
        mBinding.onContentClickListener = this
    }

    override fun onClickAddLicensesItem(view: View?, layoutPosition: Int, data: PojoAddLicenses) {

    }
}