package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.fragments.ViewModelBookingInfo
import com.nala.databinding.FragmentBookingInfoBinding

class FragmentBookingInfo : FragmentBase() {

    private lateinit var mViewModelBookingInfo: ViewModelBookingInfo
    private lateinit var mBinding: FragmentBookingInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_booking_info, container, false)

        mViewModelBookingInfo = ViewModelBookingInfo(mApplication, false)
        mBinding.vmBookingInfo = mViewModelBookingInfo


        observer()


        mBinding.cosntRateReview.setOnClickListener {

            val fragment: Fragment = FragmentRateReview()
            mMainActivity.addFragment(fragment, "FragmentRateReview", "FragmentRateReview")
        }

        return mBinding.root
    }


    fun observer() {


        mViewModelBookingInfo.getEventBack().observe(mActivity) {
            mMainActivity.onBackPressed()
        }


    }
}