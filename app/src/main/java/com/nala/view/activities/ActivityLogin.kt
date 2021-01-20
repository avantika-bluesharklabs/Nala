package com.nala.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelLogin
import com.nala.databinding.ActivityLoginBinding

class ActivityLogin : ActivityBase() {

    private lateinit var mViewModelLogin: ViewModelLogin
    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mViewModelLogin =
            ViewModelLogin(
                mApplication
            )
        mBinding.vmLogin = mViewModelLogin

        observable()

    }


    fun observable() {

        mViewModelLogin.getLiveEventSignUp().observe(this, {
            startActivity(Intent(this, ActivitySignUp::class.java))
//            startActivity(Intent(this, ActivityHomeDetails::class.java))
//            startActivity(Intent(this, ActivityBookingInfo::class.java))
        })


        mViewModelLogin.getLiveEventSignIn().observe(this, {
            startActivity(Intent(this, ActivitySignIn::class.java))
        })

        mViewModelLogin.getLiveEventBackPress().observe(this, {
           finish()
        })

        mViewModelLogin.getLiveEventSignInFacebook().observe(this, {
            mApplication!!.showLogs("LoginCLick", "Facebook CLicked")
        })

        mViewModelLogin.getLiveEventSignInApple().observe(this, {
            mApplication!!.showLogs("LoginCLick", "Apple CLicked")
        })

        mViewModelLogin.getLiveEventSignInGoogle().observe(this, {
            mApplication!!.showLogs("LoginCLick", "Google CLicked")
        })
    }



}