package com.nala.businesslogic.viewmodel

import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import com.bumptech.glide.request.RequestOptions
import com.nala.R
import com.nala.businesslogic.interactors.ObservableString
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.rx.SchedulerProvider
import com.nala.businesslogic.sharedpreference.UtilsSharedPreferences
import com.nala.view.MyApplication
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class ViewModelCommon<X>(var application: MyApplication, var isToShowErrors: Boolean) {

    protected var mApplication: MyApplication? = null
    protected var mPreferences: UtilsSharedPreferences? = null
    private var mSchedulerProvider: SchedulerProvider? = null
    private var mIsToShowErrors:Boolean = false
    private  var mIsFromSetHours:Boolean = false
    var requestOptionCentreProfilePic: RequestOptions? = null
    var requestOptionCentrePic: RequestOptions? = null

    var observerProgressBar = ObservableBoolean(false)
    var observerSnackBarInt : ObservableInt = ObservableInt()
    var observerSnackBarString: ObservableString = ObservableString("")
    var observerNoRecords: ObservableInt = ObservableInt(R.string.text_norecordfound)
    var observerNoRecordsVisibility = ObservableBoolean(false)
    var observerDataVisibility = ObservableBoolean(false)

    init {
        mApplication = application
        mPreferences = application.getAppComponent().getPreferences()
        mSchedulerProvider = mApplication!!.getAppComponent().getSchedulerProvider()

        mIsToShowErrors = isToShowErrors
        requestOptionCentreProfilePic =
            application.glideCenterCircle()

        /*requestOptionCentrePic =
            application.glideCenterCropCircle(R.drawable.add_member_profile)*/
    }


    /*  open fun ViewModelCommon(application: MyApplication, isToShowErrors: Boolean) {
          mApplication = application
          mPreferences = application.getAppComponent().getPreferences()
          mIsToShowErrors = isToShowErrors
          requestOptionCentreProfilePic =
              application.glideCenterCircle(R.drawable.ic_profile)
      }*/

   /* open fun ViewModelCommon(
        application: MyApplication,
        isToShowErrors: Boolean,
        isSetHours: Boolean
    ) {
        mApplication = application
        mPreferences = application.getAppComponent().getPreferences()
        mIsToShowErrors = isToShowErrors
        mIsFromSetHours = isSetHours
        requestOptionCentreProfilePic =
            application.glideCenterCircle(R.drawable.ic_profile)
    }*/

    open fun getPostData() {
        if (mApplication!!.isInternetConnected()) {
            observerProgressBar.set(mIsToShowErrors)
            networkCallData()
        } else {
            if (mIsToShowErrors) {
                observerSnackBarInt.set(R.string.message_noconnection)
            }
        }
    }

    abstract fun networkCallData()

    open fun isIsToShowErrors(): Boolean {
        return mIsToShowErrors
    }

    open fun setIsToShowErrors(mIsToShowErrors: Boolean) {
        this.mIsToShowErrors = mIsToShowErrors
        observerProgressBar.set(mIsToShowErrors)
    }

    abstract fun sendResponseBodyData(data: X?)

    /**
     * Callback for network call
     */
    protected var mCallbackData: Callback<X> = object : Callback<X> {
        override fun onResponse(call: Call<X>, response: Response<X>) {
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()
                val commonResponse = body as PojoCommonResponse
                if (commonResponse.getStatus()!!) {
                    //clearDataOnResponse();
                    // mTotalCount = commonResponse.getTotalCount();
                    sendResponseBodyData(response.body())
                }else if(!TextUtils.isEmpty(commonResponse.getMessage()))
                {
                    observerSnackBarString.set(commonResponse.getMessage())
                }
                else {
                    observerSnackBarInt.set(R.string.message_something_wrong)
                }
            } else if(response.errorBody() != null) {
                try {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    val message = jObjError.getString("message")
                    if(!TextUtils.isEmpty(message))
                            observerSnackBarString.set(message)
                    else
                        observerSnackBarInt.set(R.string.message_something_wrong)
                } catch (e: Exception) {
                    observerSnackBarInt.set(R.string.message_something_wrong)
                }
            }
            else
            {
                observerSnackBarInt.set(R.string.message_something_wrong)
            }
            observerProgressBar.set(false)
        }

        override fun onFailure(call: Call<X?>, t: Throwable) {
            if (mIsToShowErrors) {
                observerNoRecordsVisibility.set(true)
                observerSnackBarInt.set(R.string.message_something_wrong)
            }
            observerProgressBar.set(false)
        }
    }

    open fun setProgressBar(visible: Boolean?) {
        observerProgressBar.set(visible!!)
    }
}