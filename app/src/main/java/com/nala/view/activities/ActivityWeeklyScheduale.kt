package com.nala.view.activities


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickWeeklyDaySchedule
import com.nala.businesslogic.interfaces.OnClickWeeklyTimeSchedule
import com.nala.businesslogic.pojo.PojoWeeklyDaySchedule
import com.nala.businesslogic.pojo.PojoWeeklyTimeSchedule
import com.nala.businesslogic.viewmodel.activities.ViewModelWeeklyDaySchedule
import com.nala.businesslogic.viewmodel.activities.ViewModelWeeklyTimeSchedule
import com.nala.databinding.ActivityWeeklySchedualeBinding
import com.nala.view.adapter.AdapterMassageType
import com.nala.view.adapter.AdapterWeeklyDaySchedule


class ActivityWeeklyScheduale :  ActivityBase(), OnClickWeeklyDaySchedule,OnClickWeeklyTimeSchedule {


    private lateinit var mViewModelWeeklyDaySchedule: ViewModelWeeklyDaySchedule
    private lateinit var mViewModelWeeklyTimeSchedule: ViewModelWeeklyTimeSchedule

    private lateinit var mBinding:ActivityWeeklySchedualeBinding

    lateinit var adapterWeeklyDaySchedule: AdapterWeeklyDaySchedule


    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_weekly_scheduale)


        mViewModelWeeklyDaySchedule = ViewModelWeeklyDaySchedule(mApplication, true)
        mBinding.vmWeeklyDayScheduale = mViewModelWeeklyDaySchedule
        mBinding.onContentClickListener = this

        mViewModelWeeklyTimeSchedule = ViewModelWeeklyTimeSchedule(mApplication, true)
        mBinding.vmWeeklyTimeScheduale = mViewModelWeeklyTimeSchedule
        mBinding.onContentClickTimeListener = this

        observable()

    }

    override fun onClickWeeklyDayScheduleItem(
        view: View?,
        layoutPosition: Int,
        data: PojoWeeklyDaySchedule
    ) {

        adapterWeeklyDaySchedule = mBinding.recyclerWeekly.adapter as AdapterWeeklyDaySchedule

        adapterWeeklyDaySchedule.selected(data.txt_day!!)

       /* if( mViewModelWeeklyDaySchedule.observerContent.get(layoutPosition)?.isSelected == true){

            mViewModelWeeklyDaySchedule.observerContent.get(layoutPosition)?.isSelected = false

        }else{

            mViewModelWeeklyDaySchedule.observerContent.get(layoutPosition)?.isSelected = true
        }


        adapterWeeklyDaySchedule = mBinding.recyclerWeekly.adapter as AdapterWeeklyDaySchedule

        adapterWeeklyDaySchedule.notifyDataSetChanged()*/

    }

    override fun onClickWeeklyTimeScheduleItem(
        view: View?,
        layoutPosition: Int,
        data: PojoWeeklyTimeSchedule
    ) {

    }

    private fun observable() {

        mViewModelWeeklyDaySchedule.getLiveEventBackPress().observe(this, {
            onBackPressed()
        })

        mViewModelWeeklyDaySchedule.getLiveSetSchedule().observe(this, {

            if (it) {

                startActivity(Intent(this, ActivityCalender::class.java))

            }


        })


    }
}