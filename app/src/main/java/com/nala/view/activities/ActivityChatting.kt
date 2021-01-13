package com.nala.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.nala.R

class ActivityChatting : ActivityBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorSemiLightGray);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)
    }
}