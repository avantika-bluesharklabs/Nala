package com.nala.view.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.nala.R

class ActivityHomeDetails : ActivityBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSemiLightGray);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_details)
    }
}