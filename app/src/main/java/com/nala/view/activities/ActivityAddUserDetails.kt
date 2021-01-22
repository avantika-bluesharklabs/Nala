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
import com.nala.businesslogic.viewmodel.activities.ViewModelAddDetails
import com.nala.businesslogic.viewmodel.activities.ViewModelSignUp
import com.nala.databinding.ActivityAddUserDetailsBinding
import com.nala.databinding.ActivitySignUpBinding
import com.nala.utils.Utils
import kotlinx.android.synthetic.main.activity_add_user_details.*
import kotlinx.android.synthetic.main.activity_sign_up.*


class ActivityAddUserDetails : ActivityBase() {

    private lateinit var mViewModelAddDetails: ViewModelAddDetails
    private lateinit var mBinding: ActivityAddUserDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_user_details)
        mViewModelAddDetails =
            ViewModelAddDetails(
                mApplication,
                true
            )
        mBinding.vmAddDetails = mViewModelAddDetails

        mBinding.imgBack.setOnClickListener { finish() }

        observable();
        selectMale()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun observable() {


        mViewModelAddDetails.getLiveEventBackPress().observe(this, {
            onBackPressed()
        })


        mViewModelAddDetails.getLiveEventGender().observe(this, {
            if (it != 0) {
                selectFemale()
            } else {
                selectMale()
            }
        })



        mViewModelAddDetails.getLiveEventSubmit().observe(this, {

            Utils.hideKeyboard(this@ActivityAddUserDetails)

            if (it) {
                startActivity(Intent(this, ActivityPhoneVerification::class.java))
                finish()

//                mViewModelSignUp.networkCallData()
            }
        })
    }


    private fun selectMale() {

        txt_female.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivityAddUserDetails, R.color.colorSemiLightGray)
            )
        )
        txt_male.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivityAddUserDetails, R.color.colorWhite)
            )
        )

        txt_female.setBackgroundResource(R.drawable.curve_view_360_right_half_with_border)
        txt_male.setBackgroundResource(R.drawable.curve_view_360_left_half_with_border_selected)
    }

    private fun selectFemale() {


        txt_female.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivityAddUserDetails, R.color.colorWhite)
            )
        )
        txt_male.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivityAddUserDetails, R.color.colorSemiLightGray)
            )
        )

        txt_female.setBackgroundResource(R.drawable.curve_view_360_right_half_with_border_selected)
        txt_male.setBackgroundResource(R.drawable.curve_view_360_left_half_with_border)

    }
}