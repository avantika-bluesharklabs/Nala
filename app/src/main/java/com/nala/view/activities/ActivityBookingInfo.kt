package com.nala.view.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.nala.R

class ActivityBookingInfo : ActivityBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSemiLightGray);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_info)

        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorLightGrayUser))
    }
}