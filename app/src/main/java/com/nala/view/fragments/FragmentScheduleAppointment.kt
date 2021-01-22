package com.nala.view.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickSchedualeAppoinment
import com.nala.businesslogic.interfaces.OnClickSchedualeAppoinmentType
import com.nala.businesslogic.pojo.PojoSchedualeAppoinment
import com.nala.businesslogic.pojo.PojoSchedualeAppoinmentType
import com.nala.businesslogic.viewmodel.fragments.ViewModelScheduleAppointmentTime
import com.nala.businesslogic.viewmodel.fragments.ViewModelScheduleAppointmentType
import com.nala.databinding.FragmentScheduleAppointmentBinding
import com.nala.view.activities.ActivityMain

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

        observer()

        mBinding.btnSignUp.setOnClickListener {

            showDialog()
        }


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

    fun observer() {


        mViewModelSchedualeAppoinment.getEventBack().observe(mActivity) {
            mMainActivity.onBackPressed()
        }
    }


    private fun showDialog() {
        val dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.popup_success)
        val btn_see_request = dialog.findViewById(R.id.btn_see_request) as AppCompatButton

        btn_see_request.setOnClickListener {

            dialog.dismiss()

            val fragment: Fragment = FragmentMyBookings()
            mMainActivity.addFragment(fragment, "FragmentMyBooking", "FragmentMyBooking")


        }



        dialog.show()

    }
}