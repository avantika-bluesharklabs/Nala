package com.nala.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.nala.R


/**
 * Created by Avantika Gadhiya on 3/29/2020.
 */
class ActivitySplash : ActivityBase() {


    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        beginNavigationThread()
        generateToken()

    }

    private fun beginNavigationThread() {
        Thread(Runnable {
            try {
                Thread.sleep(3000)
            } catch (e: Exception) {
            }
            navigateFromSplash()
        }).start()
    }

    fun navigateFromSplash() {

        mApplication.showLogs(
            "checkLogin",
            "boolean is   " + mPreferences.getBoolean(R.string.pref_is_user_login)
        )

        val intent = Intent(this, ActivityLogin::class.java)
        startActivity(intent)
        finish()

       /* if (mPreferences.getBoolean(R.string.pref_is_user_login)) {

          //  startActivity(Intent(this, ActivityRateTherapist::class.java))

            val intent = Intent(this, ActivityMain::class.java)
            startActivity(intent)
            finish()

        } else {

            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
            finish()


        }*/
    }

    private fun generateToken() {

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            mPreferences.setString(R.string.pref_fcm_token, token.toString())

        })
    }


}