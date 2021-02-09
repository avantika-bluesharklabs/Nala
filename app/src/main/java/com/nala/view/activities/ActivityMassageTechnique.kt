package com.nala.view.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ViewBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickMassageType
import com.nala.businesslogic.pojo.PojoMassageType
import com.nala.businesslogic.viewmodel.activities.ViewModelForgotPassword
import com.nala.businesslogic.viewmodel.activities.ViewModelMassageTechnique
import com.nala.databinding.ActivityMassageTechniqueBinding
import com.nala.view.adapter.AdapterMassageType
import java.util.ArrayList

class ActivityMassageTechnique : ActivityBase(), OnClickMassageType {

    private lateinit var mViewModelMassageTechnique: ViewModelMassageTechnique
    private lateinit var mBinding: ActivityMassageTechniqueBinding
    lateinit var adapterMassageType:AdapterMassageType


    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_massage_technique)
        mViewModelMassageTechnique = ViewModelMassageTechnique(mApplication, true)
        mBinding.vmMassageTechnique = mViewModelMassageTechnique
        mBinding.onContentClickListener = this

        observable()


    }

    private fun observable() {

        mViewModelMassageTechnique.getLiveEventBackPress().observe(this, {
            onBackPressed()
        })

        mViewModelMassageTechnique.getLiveEventContinue().observe(this, {

            if (it) {
                startActivity(Intent(this, ActivityMassageCharges::class.java))

            }


        })


    }

    override fun onClickMassageTypeItem(view: View?, layoutPosition: Int, data: PojoMassageType) {

        if( mViewModelMassageTechnique.observerContent.get(layoutPosition)?.isSelected == true){

            mViewModelMassageTechnique.observerContent.get(layoutPosition)?.isSelected = false


        }else{

            mViewModelMassageTechnique.observerContent.get(layoutPosition)?.isSelected = true
        }



        adapterMassageType = mBinding.recycleMassageType.adapter as AdapterMassageType

        adapterMassageType.notifyDataSetChanged()

    }
}