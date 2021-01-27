package com.nala.view.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelSignIn
import com.nala.businesslogic.viewmodel.activities.ViewModelSignUp
import com.nala.databinding.ActivitySignUpBinding

import com.nala.utils.Utils
import kotlinx.android.synthetic.main.activity_sign_up.*

class ActivitySignUp : ActivityBase() {

    private lateinit var mViewModelSignUp: ViewModelSignUp
    private lateinit var mBinding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        mViewModelSignUp =
            ViewModelSignUp(
                mApplication,
                true
            )
        mBinding.vmSignUp = mViewModelSignUp


        mBinding.imgBack.setOnClickListener { finish() }

        observable()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun observable() {


        mViewModelSignUp.getLiveEventBackPress().observe(this, {
            onBackPressed()
        })


        mViewModelSignUp.getLiveEventUser().observe(this, {

        })



        mViewModelSignUp.getLiveEventSignUp().observe(this, {

            Utils.hideKeyboard(this@ActivitySignUp)

            if (it) {
                startActivity(Intent(this, ActivityAddUserDetails::class.java))
                finish()

//                mViewModelSignUp.networkCallData()
            }
        })
    }



}