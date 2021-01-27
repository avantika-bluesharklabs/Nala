package com.nala.view.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelForgotPassword
import com.nala.businesslogic.viewmodel.activities.ViewModelMassageTechnique
import com.nala.databinding.ActivityForgotPasswordBinding
import com.nala.databinding.ActivityMassageTechniqueBinding
import java.util.ArrayList

class ActivityMassageTechnique :ActivityBase() {

    private lateinit var mViewModelMassageTechnique: ViewModelMassageTechnique
    private lateinit var mBinding: ActivityMassageTechniqueBinding

    var list:ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_massage_technique)

        mViewModelMassageTechnique = ViewModelMassageTechnique(mApplication, true)

        mBinding.vmMassageTechnique = mViewModelMassageTechnique


    }
}