package com.nala.view.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickMyBooking
import com.nala.businesslogic.pojo.PojoMyBooking
import com.nala.businesslogic.viewmodel.fragments.ViewModelMyBookings
import com.nala.databinding.FragmentMyBookingsBinding
import kotlinx.android.synthetic.main.layout_my_booking_filters.*
import java.util.*


class FragmentMyBookings() : FragmentBase(), OnClickMyBooking {

    private lateinit var mViewModelMyBooking: ViewModelMyBookings
    private lateinit var mBinding: FragmentMyBookingsBinding
    private var date: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_my_bookings, container, false)

        mViewModelMyBooking = ViewModelMyBookings(mApplication, false)
        mBinding.vmMyBooking = mViewModelMyBooking
        mBinding.onContentClickListener = this

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        mBinding.imgFilter.setOnClickListener {


            val view: View = layoutInflater.inflate(R.layout.layout_my_booking_filters, null)
            val dialog = BottomSheetDialog(mContext, R.style.BottomSheetDialogTheme) // Style here

            dialog.setContentView(view)
            dialog.show()


            dialog.img_date.setOnClickListener {


                val datePickerDialog = DatePickerDialog(
                    mContext, DatePickerDialog.OnDateSetListener
                    { view, year, monthOfYear, dayOfMonth ->


                        edt_date.setText(
                            year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString())

                        date =
                            year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString()

                    }, year, month, day
                )
                datePickerDialog.show()


            }

        }

        return mBinding.root
    }


    override fun onClickMyBookingItem(view: View?, layoutPosition: Int, data: PojoMyBooking) {

        val fragment: Fragment = FragmentBookingInfo()
        mMainActivity?.addFragment(fragment, "FragmentBookingInfo", "FragmentBookingInfo")


    }

    override fun onClickMyBookingCancel(view: View?, layoutPosition: Int, data: PojoMyBooking) {

        /* val fragment: Fragment = FragmentBookingInfo()
         mMainActivity?.addFragment(fragment, "FragmentBookingInfo", "FragmentBookingInfo")*/

    }


}