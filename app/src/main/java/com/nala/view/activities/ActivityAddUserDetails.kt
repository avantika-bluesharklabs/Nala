package com.nala.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelAddDetails
import com.nala.businesslogic.viewmodel.activities.ViewModelSignUp
import com.nala.databinding.ActivityAddUserDetailsBinding
import com.nala.databinding.ActivitySignUpBinding

class ActivityAddUserDetails : ActivityBase() {

//    private lateinit var mViewModelAddDetails: ViewModelAddDetails
    private lateinit var mBinding: ActivityAddUserDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_user_details)
//        mViewModelAddDetails =
//            ViewModelAddDetails(
//                mApplication,
//                true
//            )
//        mBinding.vmAddDetails = mViewModelAddDetails
    }
}