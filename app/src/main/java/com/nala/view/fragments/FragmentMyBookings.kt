package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nala.R
import kotlinx.android.synthetic.main.fragment_my_bookings.*

class FragmentMyBookings : FragmentBase() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_my_bookings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_filter.setOnClickListener(View.OnClickListener { openFilters() })

    }


    fun openFilters() {
        val btnsheet = layoutInflater.inflate(R.layout.layout_my_booking_filters, null)
        val dialog = BottomSheetDialog(this.requireContext())
        dialog.setContentView(btnsheet)
        btnsheet.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}