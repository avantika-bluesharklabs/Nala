package com.nala.view.activities

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelPhoneVerification
import com.nala.databinding.ActivityPhoneVerificationBinding

class ActivityPhoneVerification : ActivityBase() {

    private lateinit var mViewModelPhoneVerification: ViewModelPhoneVerification
    private lateinit var mBinding: ActivityPhoneVerificationBinding

    var mPhoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_phone_verification)
        mViewModelPhoneVerification = ViewModelPhoneVerification(mApplication, true)
        mBinding.vmVerifyMobile = mViewModelPhoneVerification

        observable()
    }


    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.popup_account_created)
        val body = dialog.findViewById(R.id.img_done) as AppCompatImageView
//        body.text = title
//        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
//        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
//        yesBtn.setOnClickListener {
//            dialog.dismiss()
//        }
        body.setOnClickListener {  startActivity(Intent(this, ActivityMain::class.java))
            finish() }
        dialog.show()

    }


    fun observable() {

        mViewModelPhoneVerification.getLiveEventBackPress().observe(this, {
            onBackPressed()
            finish()
        })

        mViewModelPhoneVerification.getLiveEventSubmit().observe(this, {

            val sb = StringBuilder()
            sb.append(mBinding.edtOtpOne.text)
            sb.append(mBinding.edtOtpTwo.text)
            sb.append(mBinding.edtOtpThree.text)
            sb.append(mBinding.edtOtpFour.text)

            mViewModelPhoneVerification.mOtp = sb.toString()

            showDialog()
//            mViewModelPhoneVerification.networkCallData()

        })

    }
}