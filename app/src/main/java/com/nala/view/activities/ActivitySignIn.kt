package com.nala.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.ViewModelSignIn
import com.nala.databinding.ActivitySignInBinding

class ActivitySignIn : ActivityBase() {


    private lateinit var mViewModelSignIn: ViewModelSignIn
    private lateinit var mBinding: ActivitySignInBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        mViewModelSignIn = ViewModelSignIn(mApplication, true)
        mBinding.vmSignIn = mViewModelSignIn

        observable()
    }


    fun observable() {

        mViewModelSignIn.getLiveEventBackPress().observe(this, {
            startActivity(Intent(this, ActivityLogin::class.java))
            finish()
        })

        mViewModelSignIn.getLiveEventSignIn().observe(this, {

            mApplication!!.showLogs("SignInCLick", "SignIn")
        })

        mViewModelSignIn.getLiveEventForgotPass().observe(this, {

            mApplication!!.showLogs("SignInCLick", "ForgotPass")
        })
    }
}