package com.nala.view.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.nala.R

class ActivityScheduleAppointment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_appointment)
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
}