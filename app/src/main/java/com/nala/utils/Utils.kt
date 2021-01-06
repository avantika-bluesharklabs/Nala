package com.nala.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.nala.R
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Avantika Gadhiya on 3/29/2020.
 */

class Utils {
    companion object {
        fun hideKeyboard(activity: Activity) {
            try {
                val inputMethodManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                if (activity.currentFocus != null) {
                    val iBinder = activity.currentFocus!!.windowToken
                    if (iBinder != null) {
                        inputMethodManager.hideSoftInputFromWindow(iBinder, 0)
                    }
                }
            } catch (exception: Exception) {
                Logger.log(exception.toString())
            }
        }

        fun showSnackBar(viewLayout: View, toastMessage: String) {
            try {
                Snackbar.make(viewLayout, toastMessage, Snackbar.LENGTH_LONG).show()
            } catch (exception: Exception) {
                Logger.log(exception.toString())
            }
        }


        fun setSpinnerTextPadding(isDropDown: Boolean, context: Context, textView: TextView) {
            if (isDropDown) {
                textView.setPadding(
                    context.resources.getDimensionPixelSize(R.dimen.margin_eight),
                    context.resources.getDimensionPixelSize(R.dimen.margin_eight),
                    context.resources.getDimensionPixelSize(R.dimen.margin_eight),
                    context.resources.getDimensionPixelSize(R.dimen.margin_eight)
                )
            } else {
                textView.setPadding(
                    0, 0, 0,
                    0
                )
            }
        }
    }
}