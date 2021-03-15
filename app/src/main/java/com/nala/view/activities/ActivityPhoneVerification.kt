package com.nala.view.activities

import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.Window
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelPhoneVerification
import com.nala.databinding.ActivityPhoneVerificationBinding
import com.nala.utils.Utils
import com.nala.utils.Utils.Companion.hideKeyboard

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
        moveIndexes()

        mBinding.imgBack.setOnClickListener { finish() }

        observable()
    }


    /* private fun showDialog() {
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
         body.setOnClickListener {  startActivity(Intent(this, ActivityAddUserDetails::class.java))
             finish() }
         dialog.show()

     }*/


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

            if (it) {
                startActivity(Intent(this, ActivityAddUserDetails::class.java))

            }
//            mViewModelPhoneVerification.networkCallData()

        })

    }

    fun moveIndexes() {
        mBinding.edtOtpOne.requestFocus()
        mBinding.edtOtpOne.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                mBinding.edtOtpOne.requestFocus()

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                mBinding.edtOtpOne.inputType = InputType.TYPE_CLASS_NUMBER


                //    edt_otp_1.transformationMethod = PasswordTransformationMethod.getInstance()

            }

            override fun afterTextChanged(editable: Editable) {
                mBinding.edtOtpTwo.requestFocus()

            }
        })


        mBinding.edtOtpTwo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mBinding.edtOtpTwo.inputType = InputType.TYPE_CLASS_NUMBER
                //  edt_otp_2.transformationMethod = PasswordTransformationMethod.getInstance()


            }

            override fun afterTextChanged(s: Editable?) {

                mBinding.edtOtpThree.requestFocus()

            }

        })

        mBinding.edtOtpThree.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mBinding.edtOtpThree.inputType = InputType.TYPE_CLASS_NUMBER
                //   edt_otp_3.transformationMethod = PasswordTransformationMethod.getInstance()


            }

            override fun afterTextChanged(s: Editable?) {
                mBinding.edtOtpFour.requestFocus()

            }

        })

        mBinding.edtOtpFour.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mBinding.edtOtpFour.inputType = InputType.TYPE_CLASS_NUMBER
                //   edt_otp_5.transformationMethod = PasswordTransformationMethod.getInstance()


            }

            override fun afterTextChanged(s: Editable?) {

                hideKeyboard(this@ActivityPhoneVerification)

            }
        })


    }


}