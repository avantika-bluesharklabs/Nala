package com.nala.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickAddLicenses
import com.nala.businesslogic.pojo.PojoAddLicenses
import com.nala.businesslogic.viewmodel.activities.ViewModelAddLicenses
import com.nala.databinding.ActivityAddLicensesBinding


class ActivityAddLicenses : ActivityBase(), OnClickAddLicenses {

    private lateinit var mViewModelAddLicenses: ViewModelAddLicenses
    private lateinit var mBinding: ActivityAddLicensesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_licenses)
        mViewModelAddLicenses = ViewModelAddLicenses(mApplication, true)
        mBinding.vmAddLicenses = mViewModelAddLicenses
        mBinding.onContentClickListener = this

        observable()




    }


    override fun onClickAddLicensesItem(view: View?, layoutPosition: Int, data: PojoAddLicenses) {


    }

    private fun observable() {

        mViewModelAddLicenses.getLiveEventBackPress().observe(this, {
            onBackPressed()
        })

        mViewModelAddLicenses.getLiveEventContinue().observe(this, {

            if (it) {
                startActivity(Intent(this, ActivityWeeklyScheduale::class.java))

            }


        })


    }


}