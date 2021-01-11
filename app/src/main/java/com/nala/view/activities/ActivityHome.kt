package com.nala.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nala.R

class ActivityHome : ActivityBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}