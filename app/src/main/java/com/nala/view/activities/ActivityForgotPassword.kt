package com.nala.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelForgotPassword
import com.nala.databinding.ActivityForgotPasswordBinding

class ActivityForgotPassword : ActivityBase() {

    private lateinit var mViewModelForgotPass: ViewModelForgotPassword
    private lateinit var mBinding: ActivityForgotPasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        mViewModelForgotPass =
            ViewModelForgotPassword(
                mApplication,
                true
            )
        mBinding.vmForgotPass = mViewModelForgotPass

        observable()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun observable() {

        mViewModelForgotPass.getLiveEventBackPress().observe(this, {
            onBackPressed()
        })

        mViewModelForgotPass.getLiveEventForgotPass().observe(this, {

            if (it) {
                startActivity(Intent(this, ActivityPhoneVarification::class.java))

            }


        })


    }
}