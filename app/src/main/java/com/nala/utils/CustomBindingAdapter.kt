package com.nala.utils

import android.text.TextUtils
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import com.nala.businesslogic.interactors.ObservableString
import com.google.android.material.navigation.NavigationView

/**
 * Created by Avantika Gadhiya on 3/29/2020.
 */
object CustomBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["showSnackBarInt", "showSnackBarString"], requireAll = false)
    fun showSnackBar(
        viewLayout: View, snackMessageInt: ObservableInt, snackMessageString: ObservableString
    ) {
        var message = ""
        if (!TextUtils.isEmpty(snackMessageString.getTrimmed())) {
            message = snackMessageString.getTrimmed()
            snackMessageString.set("")
        } else if (snackMessageInt.get() != 0) {
            message = viewLayout.resources.getString(snackMessageInt.get())
            snackMessageInt.set(0)
        }
        if (!TextUtils.isEmpty(message)) {
            Utils.showSnackBar(viewLayout, message)
        }
    }

    @JvmStatic
    @BindingAdapter("navigationViewItemClick")
    fun loadNavigationHeader(
        navigationView: NavigationView,
        itemSelectedListener: NavigationView.OnNavigationItemSelectedListener
    ) {
        navigationView.setNavigationItemSelectedListener(itemSelectedListener)
    }
}