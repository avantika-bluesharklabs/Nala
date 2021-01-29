package com.nala.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.fragments.ViewModelBookingInfo
import com.nala.businesslogic.viewmodel.fragments.ViewModelMassageRquestInfo
import com.nala.databinding.FragmentBookingInfoBinding
import com.nala.databinding.FragmentMsgRequestInfoBinding


class FragmentMsgRequestInfo : FragmentBase() {

    private lateinit var mViewModelMsgInfo: ViewModelMassageRquestInfo
    private lateinit var mBinding: FragmentMsgRequestInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_msg_request_info, container, false)
        mViewModelMsgInfo = ViewModelMassageRquestInfo(mApplication, false)
        mBinding.vmMsgInfo = mViewModelMsgInfo

        observer()
        return mBinding.root

    }

    fun observer() {

        mViewModelMsgInfo.getEventBack().observe(mActivity) {
            mMainActivity?.onBackPressed()
        }


    }


}