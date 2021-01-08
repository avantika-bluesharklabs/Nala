package com.nala.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelSignIn
import com.nala.databinding.ActivitySignInBinding
import com.nala.utils.Utils

class ActivitySignIn : ActivityBase() {


    private lateinit var mViewModelSignIn: ViewModelSignIn
    private lateinit var mBinding: ActivitySignInBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        mViewModelSignIn =
            ViewModelSignIn(
                mApplication,
                true
            )
        mBinding.vmSignIn = mViewModelSignIn

        observable()
    }


    fun observable() {

        mViewModelSignIn.getLiveEventBackPress().observe(this, {
            startActivity(Intent(this, ActivityLogin::class.java))
            finish()
        })



        mViewModelSignIn.getLiveEventSignIn().observe(this, {

            Utils.hideKeyboard(this@ActivitySignIn)
            mApplication.showLogs("SignInCLick", "SignIn   " + it)
            if (it) {
                mViewModelSignIn.networkCallData()
            }
        })

        mViewModelSignIn.getLiveEventForgotPass().observe(this, {
            startActivity(Intent(this, ActivityForgotPassword::class.java))
            finish()
        })
    }
}