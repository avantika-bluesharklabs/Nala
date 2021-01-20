package com.nala.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickReview
import com.nala.businesslogic.interfaces.OnClickTechnique
import com.nala.businesslogic.pojo.PojoReview
import com.nala.businesslogic.pojo.PojoTechnique
import com.nala.businesslogic.viewmodel.activities.ViewModelReview
import com.nala.businesslogic.viewmodel.activities.ViewModelTechnique
import com.nala.databinding.ActivityServiceProBinding

class ActivityServicePro : ActivityBase(),OnClickTechnique,OnClickReview {

    lateinit var mViewModelTechnique: ViewModelTechnique
    lateinit var mViewModelReview: ViewModelReview
    private lateinit var mBinding: ActivityServiceProBinding


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSemiLightGray);
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_service_pro)
        mViewModelTechnique = ViewModelTechnique(mApplication, true)
        mViewModelReview = ViewModelReview(mApplication, true)
        mBinding.vmTechnique = mViewModelTechnique
        mBinding.vmReview = mViewModelReview
        mBinding.onContentClickListener = this
        mBinding.onContentClickListenerReview = this




       /* mBinding.txtTech.setTextColor(R.color.colorPrimary)
        mBinding.llTechDiv.setBackgroundColor(R.color.colorPrimary)

        mBinding.rvTechniques.visibility = View.VISIBLE*/


        mBinding.clReviews.setOnClickListener {

            mBinding.rvTechniques.visibility = View.GONE
            mBinding.rvReviews.visibility = View.VISIBLE



            mBinding.txtReview.setTextColor(R.color.colorPrimary)
            mBinding.llReviewDiv.setBackgroundColor(R.color.colorPrimary)

            mBinding.txtTech.setTextColor(R.color.colorSemiLightGray)
            mBinding.llTechDiv.setBackgroundColor(R.color.colorSemiLightGray)

        }

        mBinding.clTechniques.setOnClickListener {

            mBinding.rvReviews.visibility = View.GONE
            mBinding.rvTechniques.visibility = View.VISIBLE


            mBinding.txtReview.setTextColor(R.color.colorSemiLightGray)
            mBinding.llReviewDiv.setBackgroundColor(R.color.colorSemiLightGray)

            mBinding.txtTech.setTextColor(R.color.colorPrimary)
            mBinding.llTechDiv.setBackgroundColor(R.color.colorPrimary)

        }




        mBinding.btnBookNow.setOnClickListener {  startActivity(Intent(mContext, ActivityScheduleAppointment::class.java)) }


    }



    override fun onClickTechniqueItem(view: View?, layoutPosition: Int, data: PojoTechnique) {

    }

    override fun onClickReviewItem(view: View?, layoutPosition: Int, data: PojoReview) {

    }
}