package com.nala.view.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nala.R
import java.util.*


class FragmentBottomsheetFilter : BottomSheetDialogFragment() {

    private var img_date: AppCompatImageView? = null
    private var txt_select: AppCompatTextView? = null
    private var date: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.layout_my_booking_filters, container, false)

        Log.d("Click","ggg")

        img_date = view?.findViewById(R.id.img_date)
        txt_select = view?.findViewById(R.id.txt_select)

      /*  img_date?.setOnClickListener {

            Log.d("Click","ggg")

            date()
        }*/



        img_date?.setOnClickListener({

            Log.d("Click","ggg")

            date()

        })


        return v

    }


    private fun date() {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //dd mmm,yyyy

        val datePickerDialog = context?.let {
            DatePickerDialog(it, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    txt_select?.setText(year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString())

                    date = year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString()

                }, year, month, day
            )
        }
        datePickerDialog?.show()


    }


}