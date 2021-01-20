package com.nala.view.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickSchedualeAppoinment
import com.nala.businesslogic.interfaces.OnClickSchedualeAppoinmentType
import com.nala.businesslogic.pojo.PojoSchedualeAppoinment
import com.nala.businesslogic.pojo.PojoSchedualeAppoinmentType
import com.nala.businesslogic.viewmodel.activities.ViewModelSchedualeAppoinmenttype
import com.nala.businesslogic.viewmodel.activities.ViewModelScheduleAppointment
import com.nala.businesslogic.viewmodel.fragments.ViewModelHome
import com.nala.databinding.ActivityHomeBinding
import com.nala.databinding.ActivityScheduleAppointmentBinding

class ActivityScheduleAppointment : ActivityBase(),OnClickSchedualeAppoinment,OnClickSchedualeAppoinmentType {

    private lateinit var mViewModelSchedualeAppoinment: ViewModelScheduleAppointment
    private lateinit var mViewModelSchedualeAppoinmentType: ViewModelSchedualeAppoinmenttype
    private lateinit var mBinding: ActivityScheduleAppointmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSemiLightGray);
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_schedule_appointment)
        mViewModelSchedualeAppoinment = ViewModelScheduleAppointment(mApplication, false)
        mViewModelSchedualeAppoinmentType = ViewModelSchedualeAppoinmenttype(mApplication, false)
        mBinding.vmSchedualeMan = mViewModelSchedualeAppoinment
        mBinding.vmSchedualeManType = mViewModelSchedualeAppoinmentType
        mBinding.onContentClickListener = this
        mBinding.onContentClickListenerType = this


        showDialog()
    }



    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.popup_success)
//        val body = dialog.findViewById(R.id.body) as TextView
//        body.text = title
//        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
//        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
//        yesBtn.setOnClickListener {
//            dialog.dismiss()
//        }
//        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

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