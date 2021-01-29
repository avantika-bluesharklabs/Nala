package com.nala.view.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
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
import java.io.ByteArrayOutputStream


class ActivityAddUserDetails : ActivityBase() {

    private lateinit var mViewModelAddDetails: ViewModelAddDetails
    private lateinit var mBinding: ActivityAddUserDetailsBinding


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_user_details)
        mViewModelAddDetails = ViewModelAddDetails(mApplication, true)
        mBinding.vmAddDetails = mViewModelAddDetails
        
        mBinding.requestOptions = mApplication.glideCenterCircle(R.drawable.add_photo)

        mBroadcastManager.registerReceiver(mReceiverImageResult, IntentFilter(resources.getString(R.string.broadcastImageResult)))



        observable();
        selectMale()

       // mBinding.set(mApplication.glideCenterCircle(R.drawable.add_photo))



        mBinding.imgUserImage.setOnClickListener {

            imageChooser(true)


        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun observable() {


        mViewModelAddDetails.getLiveEventBackPress().observe(this, {
            onBackPressed()
        })


        mViewModelAddDetails.getLiveEventGender().observe(this, {
            if (it == 0) {
                selectMale()
            } else if (it == 1 ) {
                selectFemale()
            }else {

                selectNotPecified()

            }
        })



        mViewModelAddDetails.getLiveEventSubmit().observe(this, {

            Utils.hideKeyboard(this@ActivityAddUserDetails)

            if (it) {
                startActivity(Intent(this, ActivityMassageTechnique::class.java))
                finish()

//                mViewModelSignUp.networkCallData()
            }
        })
    }


    private fun selectMale() {

        txt_not_specified.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivityAddUserDetails, R.color.colorSemiLightGray)
            )
        )

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

        txt_female.setBackgroundResource(R.drawable.curve_veiw_rectangle)
        txt_not_specified.setBackgroundResource(R.drawable.curve_view_360_right_half_with_border)
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

        txt_not_specified.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivityAddUserDetails, R.color.colorSemiLightGray)
            )
        )

        txt_female.setBackgroundResource(R.drawable.curve_veiw_rectangle_selected)
        txt_male.setBackgroundResource(R.drawable.curve_view_360_left_half_with_border)
        txt_not_specified.setBackgroundResource(R.drawable.curve_view_360_right_half_with_border)

    }

    private fun selectNotPecified() {


        txt_female.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivityAddUserDetails, R.color.colorSemiLightGray)
            )
        )
        txt_male.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivityAddUserDetails, R.color.colorSemiLightGray)
            )
        )

        txt_not_specified.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(this@ActivityAddUserDetails, R.color.colorWhite)
            )
        )

        txt_female.setBackgroundResource(R.drawable.curve_veiw_rectangle)
        txt_male.setBackgroundResource(R.drawable.curve_view_360_left_half_with_border)
        txt_not_specified.setBackgroundResource(R.drawable.curve_view_360_right_half_with_border_selected)

    }


    private val mReceiverImageResult: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val byteArray =
                intent.getByteArrayExtra(mContext.resources.getString(R.string.bundleImageResultStream))
            val outputStream = ByteArrayOutputStream(byteArray.size)
            outputStream.write(byteArray, 0, byteArray.size)
            mViewModelAddDetails.observerStreamPic.set(outputStream)
            mViewModelAddDetails.observerPicUri.set(
                intent.getStringExtra(
                    mContext.resources.getString(
                        R.string.bundleImageResultUri
                    )
                )
            )
        }
    }
}