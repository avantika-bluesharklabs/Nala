package com.nala.view.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelPhoneVarification
import com.nala.databinding.ActivityPhoneVarificationBinding

class ActivityPhoneVarification : ActivityBase() {

    private lateinit var mViewModelPhoneVerification: ViewModelPhoneVarification
    private lateinit var mBinding: ActivityPhoneVarificationBinding

    var mPhoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_phone_varification)
        mViewModelPhoneVerification = ViewModelPhoneVarification(mApplication, true)
        mBinding.vmVerifyMobile = mViewModelPhoneVerification
    }


    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.popup_account_created)
//        val body = dialog.findViewById(R.id.body) as TextView
//        body.text = title
//        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
//        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
//        yesBtn.setOnClickListener {
//            dialog.dismiss()
//        }
//        noBtn.setOnClickListener { dialog.dismiss() }
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