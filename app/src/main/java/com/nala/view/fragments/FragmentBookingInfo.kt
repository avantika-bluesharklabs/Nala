package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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


        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_booking_info, container, false)

        mViewModelBookingInfo = ViewModelBookingInfo(mApplication, false)
        mBinding.vmBookingInfo = mViewModelBookingInfo


        return mBinding.root
    }
}