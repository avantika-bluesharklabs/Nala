package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickMyBooking
import com.nala.businesslogic.pojo.PojoMyBooking
import com.nala.businesslogic.viewmodel.fragments.ViewModelMyBookings

import com.nala.databinding.FragmentMyBookingsBinding


class FragmentMyBookings() : FragmentBase(),OnClickMyBooking {

    private lateinit var mViewModelMyBooking: ViewModelMyBookings
    private lateinit var mBinding: FragmentMyBookingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_bookings, container, false)

        mViewModelMyBooking = ViewModelMyBookings(mApplication,false)
        mBinding.vmMyBooking = mViewModelMyBooking
        mBinding.onContentClickListener = this

        mBinding.imgFilter.setOnClickListener {

            BottomSheetFilter().show(mActivity.getSupportFragmentManager(), "Dialog")

        }



        return mBinding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // img_filter.setOnClickListener(View.OnClickListener { openFilters() })

    }*/


    /*fun openFilters() {
        val btnsheet = layoutInflater.inflate(R.layout.layout_my_booking_filters, null)
        val dialog = BottomSheetDialog(this.requireContext())
        dialog.setContentView(btnsheet)
        btnsheet.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }*/




    override fun onClickMyBookingItem(view: View?, layoutPosition: Int, data: PojoMyBooking) {




    }

    override fun onClickMyBookingCancel(view: View?, layoutPosition: Int, data: PojoMyBooking) {

        val fragment: Fragment = FragmentBookingInfo()
        mMainActivity.addFragment(fragment, "FragmentBookingInfo", "FragmentBookingInfo")

    }


}