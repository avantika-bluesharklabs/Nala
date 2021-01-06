package com.nala.businesslogic.listeners

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.nala.view.MyApplication

class MyFirebaseMessagingService : FirebaseMessagingService() {


    lateinit var myApplication : MyApplication

    override fun onMessageReceived(remoteMessage: RemoteMessage) {


    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }


    @SuppressLint("WrongConstant")
    private fun showNotificationMethod1(title: String, message: String, resultIntent: Intent) {
    }

    private fun showNotificationMethod2(messageBody: String) {
    }

    fun isBackgroundRunning(context: Context): Boolean {
        val am = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val runningProcesses = am.runningAppProcesses
        for (processInfo in runningProcesses) {
            if (processInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (activeProcess in processInfo.pkgList) {
                    if (activeProcess == context.packageName) {
                        //If your app is the process in foreground, then it's not in running in background
                        return false
                    }
                }
            }
        }
        return true
    }

    companion object {

        private const val TAG = "MyFirebaseMsgService"
    }

}