package com.nala.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickReview
import com.nala.businesslogic.interfaces.OnClickTechnique
import com.nala.businesslogic.pojo.PojoReview
import com.nala.businesslogic.pojo.PojoTechnique
import com.nala.businesslogic.viewmodel.fragments.ViewModelReview
import com.nala.businesslogic.viewmodel.fragments.ViewModelTechnique
import com.nala.databinding.FragmentServiceProBinding

class FragmentServicePro : FragmentBase(), OnClickTechnique, OnClickReview {


    private lateinit var mBinding: FragmentServiceProBinding
    lateinit var mViewModelTechnique: ViewModelTechnique
    lateinit var mViewModelReview: ViewModelReview

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_pro, container, false)
        mViewModelTechnique = ViewModelTechnique(mApplication, true)
        mViewModelReview = ViewModelReview(mApplication, true)
        mBinding.vmTechnique = mViewModelTechnique
        mBinding.vmReview = mViewModelReview
        mBinding.onContentClickListener = this
        mBinding.onContentClickListenerReview = this




        mBinding.clReviews.setOnClickListener {

            mBinding.rvTechniques.visibility = View.GONE
            mBinding.rvReviews.visibility = View.VISIBLE

            mBinding.txtReview.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorPrimary))
            mBinding.llReviewDiv.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorPrimary))

            mBinding.txtTech.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorSemiLightGray))
            mBinding.llTechDiv.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorSemiLightGray))

        }

        mBinding.clTechniques.setOnClickListener {

            mBinding.rvReviews.visibility = View.GONE
            mBinding.rvTechniques.visibility = View.VISIBLE


            mBinding.txtReview.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorSemiLightGray))
            mBinding.llReviewDiv.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorSemiLightGray))

            mBinding.txtTech.setTextColor(ContextCompat.getColor(requireContext(),R.color.colorPrimary))
            mBinding.llTechDiv.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorPrimary))

        }

        mBinding.btnBookNow.setOnClickListener {


            val fragment: Fragment = FragmentScheduleAppointment()
            mMainActivity.addFragment(fragment, "FragmentScheduleAppointment", "FragmentScheduleAppointment")

        }

        return mBinding.root
    }



    override fun onClickTechniqueItem(view: View?, layoutPosition: Int, data: PojoTechnique) {

    }

    override fun onClickReviewItem(view: View?, layoutPosition: Int, data: PojoReview) {

    }
}