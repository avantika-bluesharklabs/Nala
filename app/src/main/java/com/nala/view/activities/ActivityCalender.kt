package com.nala.view.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelCalender
import com.nala.businesslogic.viewmodel.activities.ViewModelContinueAs
import com.nala.databinding.ActivityCalenderBinding
import com.nala.databinding.ActivityContinueAsBinding

class ActivityCalender : ActivityBase() {

    private lateinit var mViewModelCalender: ViewModelCalender
    private lateinit var mBinding: ActivityCalenderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_calender)
        mViewModelCalender = ViewModelCalender(mApplication, true)
        mBinding.vmCalender = mViewModelCalender


        observable()

    }

    fun observable() {

        mViewModelCalender.getLiveEventBackPress().observe(this, {
            onBackPressed()
        })

        mViewModelCalender.getLiveEventClearSchedule().observe(this, {
            if (it) {

                startActivity(Intent(this, ActivitySignIn::class.java))

            }
        })

    }
}