package com.nala.view.activities

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.nala.R

class ActivityRateTherapist : ActivityBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_therapist)

    }
}