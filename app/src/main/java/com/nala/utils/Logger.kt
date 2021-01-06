package com.nala.utils

import android.util.Log
import com.nala.BuildConfig

/**
 * Created by Avantika Gadhiya on 3/29/2020.
 */
class Logger {
    companion object {
        @Synchronized
        fun e(tag: String?, message: String?) {
            Log.e(tag, message)
        }

        fun e(tag: String?, exception: Exception) {
            e(tag, exception.toString())
        }

        fun log(message: String?) {
            val stack = Throwable().fillInStackTrace()
            val trace = stack.stackTrace
            if (BuildConfig.DEBUG) {
                e(
                    trace[1].className + "." + trace[1].methodName + ":" +
                            trace[1].lineNumber, message
                )
            }
        }
    }
}