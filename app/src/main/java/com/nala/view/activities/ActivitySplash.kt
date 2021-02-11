package com.nala.view.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.nala.R
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


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
        printHashKey(mContext)

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



        if (mPreferences.getBoolean(R.string.pref_is_user_login)) {

          //  startActivity(Intent(this, ActivityRateTherapist::class.java))

            val intent = Intent(this, ActivityMain::class.java)
            startActivity(intent)
            finish()

        } else {

            val intent = Intent(this, ActivitySignIn::class.java)
            startActivity(intent)
            finish()


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


    fun printHashKey(pContext: Context) {
        try {
            val info: PackageInfo = pContext.getPackageManager()
                .getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.d("TAG","hashkey"+hashKey)
             //   Log.i(FragmentActivity.TAG, "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
         //   Log.e(FragmentActivity.TAG, "printHashKey()", e)
        } catch (e: java.lang.Exception) {
        //    Log.e(FragmentActivity.TAG, "printHashKey()", e)
        }
    }


}