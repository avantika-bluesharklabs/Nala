package com.nala.businesslogic.bindingadapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.webkit.WebView
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nala.R
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.interfaces.*
import com.nala.businesslogic.pojo.*
import com.nala.utils.Utils
import com.nala.view.adapter.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.io.ByteArrayOutputStream
import java.io.File

object BindingAdapterApp {
    @BindingAdapter("setSwipeRefreshing")
    @JvmStatic
    fun setSwipeRefreshing(swipeRefresh: SwipeRefreshLayout?, toRefresh: Boolean) {
        if (swipeRefresh != null) {
            swipeRefresh.isRefreshing = toRefresh
        }
    }

    @BindingAdapter("setEnabledSwipeRefresh", "refreshListener")
    @JvmStatic
    fun setEnabledSwipeRefresh(
        swipeRefresh: SwipeRefreshLayout?, isEnabled: Boolean,
        refreshListener: OnRefreshListener?
    ) {
        if (swipeRefresh != null) {
            swipeRefresh.isEnabled = isEnabled
            swipeRefresh.setOnRefreshListener(refreshListener)
        }
    }


    @BindingAdapter(value = ["setError", "setTILErrorString"], requireAll = false)
    @JvmStatic
    fun setError(
        textInputLayout: TextInputLayout, errorInt: Int,
        errorMessage: String?
    ) {
        if (errorInt != 0) {
            textInputLayout.error = textInputLayout.context.resources.getString(errorInt)
        } else if (!TextUtils.isEmpty(errorMessage)) {
            textInputLayout.error = errorMessage
        }
    }

    /**
     * Sets error for textinput layout
     *
     * @param spinner  Textinputlayout reference
     * @param errorInt Error message to be shown
     */
    @BindingAdapter(value = ["setError", "setTILErrorString"], requireAll = false)
    @JvmStatic
    fun setError(
        spinner: Spinner, errorInt: Int,
        errorMessage: String?
    ) {
        if (errorInt != 0) {
            (spinner.selectedView as TextView).error = spinner.context.resources.getString(errorInt)
        } else if (!TextUtils.isEmpty(errorMessage)) {
            (spinner.selectedView as TextView).error = errorMessage
        }
    }

    /**
     * Sets enable flag for textinput layout
     *
     * @param textInputLayout Textinputlayout reference
     * @param isEnabled       Whether view is enabled or not
     */
    @BindingAdapter("setEnabledTIL")
    @JvmStatic
    fun setTextInputEnabled(textInputLayout: TextInputLayout, isEnabled: Boolean) {
        textInputLayout.isEnabled = isEnabled
    }

    @BindingAdapter("passwordTransformation")
    @JvmStatic
    fun setTextInputPasswordTransformation(
        textInputEditText: TextInputEditText,
        check: Boolean
    ) {
        if (check) {
            textInputEditText.transformationMethod = PasswordTransformationMethod()
        }
    }

    @BindingAdapter(
        value = ["glideStream", "glideUrl", "glideFile", "glideResource", "glideRequestOptions"],
        requireAll = false
    )
    @JvmStatic
    fun glideStreamUrlFile(
        view: ImageView?, stream: ByteArrayOutputStream?,
        url: String?, file: File?, resource: Int,
        requestOptions: RequestOptions?
    ) {
        if (view != null) {
            val context = view.context
            val options = requestOptions ?: RequestOptions()

            if (resource > 0) {
                view.setImageResource(resource)
            } else if (file != null) {
                Glide.with(context).load(file).apply(options).into(view)
            } else if (stream != null) {
                Glide.with(context).asBitmap().load(stream.toByteArray()).apply(options).into(view)
            } else if (!TextUtils.isEmpty(url)) {
                Glide.with(context).load(url).apply(options).into(view)
            } else {
                Glide.with(context).load("").apply(options).into(view)
            }
        }
    }


    /* @BindingAdapter({"navigationViewHeader", "navigationViewHeaderClick",
            "navigationViewItemClick"})
    public static void loadNavigationHeader(NavigationView navigationView,
                                            ViewModelMain vmMain,
                                            OnClickNavHeader onClickNavHeader,
                                            NavigationView.OnNavigationItemSelectedListener
                                                    itemSelectedListener) {
        if (navigationView != null) {
            LayoutNavheaderBinding binding = LayoutNavheaderBinding.inflate(LayoutInflater.from
                    (navigationView.getContext()));
            binding.setVmMain(vmMain);
            binding.setOnClickNavHeader(onClickNavHeader);
            binding.setRequestOptions(((MyApplication) navigationView.getContext()
                    .getApplicationContext()).glideCenterCircle());
            navigationView.addHeaderView(binding.getRoot());
            navigationView.setNavigationItemSelectedListener(itemSelectedListener);
        }
    }*/
    @BindingAdapter(
        value = ["arrayStringData", "arrayData", "arrayPosition"],
        requireAll = false
    )
    @JvmStatic
    fun setSpinner(
        spinner: Spinner?, arrayData: Array<String?>?, arrayList: List<String>?,
        position: Int
    ) {
        if (spinner != null) {
            if (arrayData != null) {
                spinner.adapter = AdapterSpinner(
                    spinner.context, R.layout.spinner_rows,
                    arrayData
                )
            } else {
                val arrayString: MutableList<String> = ArrayList()
                if (arrayList != null) {
                    arrayString.addAll(arrayList)
                } /*else if (arrayTime != null) {
                    for (i in arrayTime.indices) {
                        arrayString.add(UtilsDate.get12hourTimeFormat(arrayTime[i]))
                    }
                }*/
                spinner.adapter = AdapterSpinner(
                    spinner.context, R.layout.spinner_rows,
                    arrayString
                )
            }
            spinner.setSelection(position)
        }
    }

    /**
     * Sets enable flag for spinner
     *
     * @param spinner   Spinner reference
     * @param isEnabled Whether view is enabled or not
     */
    @BindingAdapter("spinnerEnabled")
    fun setSpinnerEnabled(spinner: Spinner?, isEnabled: Boolean) {
        if (spinner != null) {
            spinner.isEnabled = isEnabled
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @BindingAdapter("textInputEditTextMultiLineParentScroll")
    fun setTextInputEditTextScroll(editText: TextInputEditText?, isToScroll: Boolean) {
        if (editText != null && isToScroll) {
            editText.setOnTouchListener(OnTouchListener { view: View, motionEvent: MotionEvent ->
                view.parent.requestDisallowInterceptTouchEvent(true)
                if (motionEvent.action and MotionEvent.ACTION_MASK == MotionEvent.ACTION_UP) {
                    view.parent.requestDisallowInterceptTouchEvent(false)
                }
                false
            })
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @BindingAdapter("editTextMultiLineParentScroll")
    fun setEditTextScroll(editText: EditText?, isToScroll: Boolean) {
        if (editText != null && isToScroll) {
            editText.setOnTouchListener(OnTouchListener { view: View, motionEvent: MotionEvent ->
                view.parent.requestDisallowInterceptTouchEvent(true)
                if (motionEvent.action and MotionEvent.ACTION_MASK == MotionEvent.ACTION_UP) {
                    view.parent.requestDisallowInterceptTouchEvent(false)
                }
                false
            })
        }
    }

    @BindingAdapter("loadText")
    fun loadText(view: WebView, text: String) {
        if (!TextUtils.isEmpty(text)) view.loadData(
            "<html><body style=\"text-align:justify;\">$text</html></body>",
            "text/html",
            "UTF-8"
        )
    }

    @BindingAdapter("loadUrl")
    fun loadUrl(view: WebView, url: String?) {
        if (!TextUtils.isEmpty(url)) view.loadUrl(url)
    }

    @BindingAdapter("floatingRecycler")
    fun setFloatingRecycler(recyclerView: RecyclerView?, isToFloat: Boolean) {
        if (recyclerView != null) {
            recyclerView.clipToPadding = !isToFloat
            recyclerView.setPadding(
                0, 0, 0, if (isToFloat) recyclerView.context.resources
                    .getDimensionPixelSize(R.dimen.margin_eighty) else 0
            )
        }
    }

    @BindingAdapter(value = ["showSnackBarInt", "showSnackBarString"], requireAll = false)
    fun showSnackBar(
        viewLayout: View, snackMessageInt: ObservableInt?,
        snackMessageString: ObservableString?
    ) {
        var message = ""
        if (snackMessageString != null && !TextUtils.isEmpty(snackMessageString.getTrimmed())) {
            message = snackMessageString.getTrimmed()
            snackMessageString.set("")
        } else if (snackMessageInt != null && snackMessageInt.get() !== 0) {
            message = viewLayout.resources.getString(snackMessageInt.get())
            snackMessageInt.set(0)
        }
        if (!TextUtils.isEmpty(message)) {
            Utils.showSnackBar(viewLayout, message)
        }
    }

}