package com.nala.view.activities

import android.content.Intent
import android.os.Bundle
import com.nala.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


/**
 * Created by Avantika Gadhiya on 3/29/2020.
 */
class ActivitySplash : ActivityBase() {



    override fun onCreate(savedInstanceState: Bundle?) {
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

        mApplication.showLogs("checkLogin","boolean is   "+mPreferences.getBoolean(R.string.pref_is_user_login))

        if (mPreferences.getBoolean(R.string.pref_is_user_login)) {



        } else {


            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)



        }
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