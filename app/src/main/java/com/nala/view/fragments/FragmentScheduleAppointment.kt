package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickSchedualeAppoinment
import com.nala.businesslogic.interfaces.OnClickSchedualeAppoinmentType
import com.nala.businesslogic.pojo.PojoSchedualeAppoinment
import com.nala.businesslogic.pojo.PojoSchedualeAppoinmentType
import com.nala.businesslogic.viewmodel.fragments.ViewModelScheduleAppointmentTime
import com.nala.businesslogic.viewmodel.fragments.ViewModelScheduleAppointmentType
import com.nala.databinding.FragmentScheduleAppointmentBinding

class FragmentScheduleAppointment : FragmentBase(), OnClickSchedualeAppoinment,
    OnClickSchedualeAppoinmentType {

    private lateinit var mViewModelSchedualeAppoinment: ViewModelScheduleAppointmentTime
    private lateinit var mViewModelSchedualeAppoinmentType: ViewModelScheduleAppointmentType
    private lateinit var mBinding: FragmentScheduleAppointmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule_appointment, container, false)

        mViewModelSchedualeAppoinment = ViewModelScheduleAppointmentTime(mApplication, false)
        mViewModelSchedualeAppoinmentType = ViewModelScheduleAppointmentType(mApplication, false)
        mBinding.vmSchedualeMan = mViewModelSchedualeAppoinment
        mBinding.vmSchedualeManType = mViewModelSchedualeAppoinmentType
        mBinding.onContentClickListener = this
        mBinding.onContentClickListenerType = this


        return mBinding.root
    }

    override fun onClickSchedualeAppoinmentItem(
        view: View?,
        layoutPosition: Int,
        data: PojoSchedualeAppoinment
    ) {
    }

    override fun onClickSchedualeAppoinmentTypeItem(
        view: View?,
        layoutPosition: Int,
        data: PojoSchedualeAppoinmentType
    ) {
    }
}