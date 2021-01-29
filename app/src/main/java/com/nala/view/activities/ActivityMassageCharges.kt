package com.nala.view.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickMassageCharges
import com.nala.businesslogic.pojo.PojoMassageCharges
import com.nala.businesslogic.viewmodel.activities.ViewModelAddDetails
import com.nala.businesslogic.viewmodel.activities.ViewModelLogin
import com.nala.businesslogic.viewmodel.activities.ViewModelMassagesCharges
import com.nala.databinding.ActivityLoginBinding
import com.nala.databinding.ActivityMassageChargesBinding

class ActivityMassageCharges : ActivityBase(),OnClickMassageCharges {

    private lateinit var mViewModelMassageCharges: ViewModelMassagesCharges
    private lateinit var mBinding: ActivityMassageChargesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_massage_charges)
        mViewModelMassageCharges = ViewModelMassagesCharges(mApplication, true)
        mBinding.vmMassageCharges = mViewModelMassageCharges
        mBinding.onContentClickListener = this

        observable()
    }

    private fun observable() {

        mViewModelMassageCharges.getLiveEventBackPress().observe(this, {
            onBackPressed()
        })

        mViewModelMassageCharges.getLiveEventContinue().observe(this, {

            if (it) {
                startActivity(Intent(this, ActivityAddLicenses::class.java))

            }


        })


    }

    override fun onClickMassageChargesItem(
        view: View?,
        layoutPosition: Int,
        data: PojoMassageCharges
    ) {

    }
}