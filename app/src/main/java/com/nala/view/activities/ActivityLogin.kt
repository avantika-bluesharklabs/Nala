package com.nala.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.ViewModelLogin
import com.nala.databinding.ActivityLoginBinding

class ActivityLogin : ActivityBase() {

     private lateinit var mViewModelLogin: ViewModelLogin
    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mViewModelLogin = ViewModelLogin(mApplication)
        mBinding.vmLogin = mViewModelLogin


        observable()

    }


    fun observable() {

//        mViewModelSignIn.getLiveEventSignUp().observe(this, {
//            startActivity(Intent(this, ActivitySignUp::class.java))
//            finish()
//        })


        mViewModelLogin.getLiveEventSignIn().observe(this, {
            startActivity(Intent(this, ActivitySignIn::class.java))
            finish()
        })

        mViewModelLogin.getLiveEventBackPress().observe(this, {
            finishAffinity()
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