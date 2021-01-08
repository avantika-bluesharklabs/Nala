package com.nala.view.activities

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelSignIn
import com.nala.businesslogic.viewmodel.activities.ViewModelSignUp
import com.nala.databinding.ActivitySignInBinding
import com.nala.databinding.ActivitySignUpBinding
import com.nala.utils.Utils
import kotlinx.android.synthetic.main.activity_sign_up.*

class ActivitySignUp : ActivityBase() {

    private lateinit var mViewModelSignUp: ViewModelSignUp
    private lateinit var mBinding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        mViewModelSignUp =
            ViewModelSignUp(
                mApplication,
                true
            )
        mBinding.vmSignUp = mViewModelSignUp

        observable()
        selectUser()
    }

    private fun observable() {


        mViewModelSignUp.getLiveEventBackPress().observe(this, {
            startActivity(Intent(this, ActivityLogin::class.java))
            finish()
        })


        mViewModelSignUp.getLiveEventUser().observe(this, {
            if (it != 0) {

                startActivity(Intent(this, ActivityAddUserDetails::class.java))
                finish()

//                selectTherapist()
            } else {
                selectUser()
            }
        })



        mViewModelSignUp.getLiveEventSignUp().observe(this, {

            Utils.hideKeyboard(this@ActivitySignUp)

            if (it) {
                mViewModelSignUp.networkCallData()
            }
        })
    }


    private fun selectUser() {
        ImageViewCompat.setImageTintList(
            img_therapist_image, ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivitySignUp, R.color.colorBlack)
            )
        )
        ImageViewCompat.setImageTintList(
            img_user_image, ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivitySignUp, R.color.colorWhite)
            )
        )

        txt_therapist_text.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivitySignUp, R.color.colorBlack)
            )
        )
        txt_user_text.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivitySignUp, R.color.colorWhite)
            )
        )

        cl_user.setBackgroundResource(R.drawable.curve_view_selected)
        cl_therapist.setBackgroundResource(R.drawable.curve_view_unselected)
    }

    private fun selectTherapist() {
        ImageViewCompat.setImageTintList(
            img_therapist_image, ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivitySignUp, R.color.colorWhite)
            )
        )
        ImageViewCompat.setImageTintList(
            img_user_image, ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivitySignUp, R.color.colorBlack)
            )
        )

        txt_therapist_text.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivitySignUp, R.color.colorWhite)
            )
        )
        txt_user_text.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivitySignUp, R.color.colorBlack)
            )
        )

        cl_therapist.setBackgroundResource(R.drawable.curve_view_selected)
        cl_user.setBackgroundResource(R.drawable.curve_view_unselected)

    }
}