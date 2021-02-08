package com.nala.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickHome
import com.nala.businesslogic.pojo.PojoHome
import com.nala.businesslogic.viewmodel.fragments.ViewModelHome
import com.nala.databinding.FragmentHomeBinding


class FragmentHome : FragmentBase(), OnClickHome {

    private lateinit var mViewModelHome: ViewModelHome
    private lateinit var mBinding: FragmentHomeBinding

  
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        mViewModelHome = ViewModelHome(mApplication, false)

        mBinding.vmHomeList = mViewModelHome

        mBinding.onContentClickListener = this

        mBinding.imgFilter.setOnClickListener {


            val view: View = layoutInflater.inflate(R.layout.layout_my_booking_filters, null)
            val dialog = BottomSheetDialog(mContext, R.style.BottomSheetDialogTheme) // Style here

            dialog.setContentView(view)
            dialog.show()


            /* img_date.setOnClickListener {

                 val c = Calendar.getInstance()
                 val year = c.get(Calendar.YEAR)
                 val month = c.get(Calendar.MONTH)
                 val day = c.get(Calendar.DAY_OF_MONTH)



                 val datePickerDialog = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                     txt_select.setText(year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString())

                     date = year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString()

                 }, year, month, day
                 )
                 datePickerDialog.show()

             }*/


        }

        return mBinding.root

    }

    override fun onClickHomeItem(view: View?, layoutPosition: Int, data: PojoHome) {

        val fragment: Fragment = FragmentMsgRequestInfo()
        mMainActivity?.addFragment(fragment, "FragmentMsgRequestInfo", "FragmentMsgRequestInfo")

    }

    /*override fun onClickHomeBookNow(view: View?, layoutPosition: Int, data: PojoHome) {

        val fragment: Fragment = FragmentServicePro()
        mMainActivity?.addFragment(fragment, "FragmentServicePro", "FragmentServicePro")

    }*/



}

