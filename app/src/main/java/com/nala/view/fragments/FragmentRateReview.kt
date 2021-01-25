package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.fragments.ViewModelRateReview
import com.nala.databinding.FragmentRateReviewBinding

class FragmentRateReview: FragmentBase() {

    private lateinit var mViewModelRateReview: ViewModelRateReview
    private lateinit var mBinding: FragmentRateReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_rate_review, container, false)

        mViewModelRateReview = ViewModelRateReview(mApplication, false)
        mBinding.vmRateReview = mViewModelRateReview

        observer()

        return mBinding.root
    }

    fun observer() {


        mViewModelRateReview.getEventBack().observe(mActivity) {
            mMainActivity?.onBackPressed()
        }


    }
}