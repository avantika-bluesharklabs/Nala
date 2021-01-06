package com.nala.businesslogic.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import com.nala.utils.ConstantCodes.Companion.PREF_APP_NAME
import javax.inject.Inject

/**
 * Created by Avantika Gadhiya on 3/31/2020.
 */
class UtilsSharedPreferences {
    private lateinit var mPreferences: SharedPreferences
    private lateinit var mContext: Context

    @Inject
    constructor(context: Context) {
        this.mContext = context
        this.mPreferences = context.getSharedPreferences(PREF_APP_NAME, Context.MODE_PRIVATE)
    }

    fun getBoolean(prefName: Int): Boolean {
        return mPreferences.getBoolean(mContext.resources.getString(prefName), false)
    }

    fun getBoolean(prefName: Int, defaultValue: Boolean): Boolean {
        return mPreferences.getBoolean(mContext.resources.getString(prefName), defaultValue)
    }

    fun setBoolean(prefName: Int, value: Boolean) {
        mPreferences.edit().putBoolean(mContext.resources.getString(prefName), value).apply()
    }

    fun getString(prefName: Int): String {
        return mPreferences.getString(mContext.resources.getString(prefName), "")!!
    }

    fun getString(prefName: String?): String {
        return mPreferences.getString(prefName, "")!!
    }

    fun getString(prefName: Int, defaultValue: String): String {
        return mPreferences.getString(mContext.resources.getString(prefName), defaultValue)!!
    }


    fun setString(prefName: Int, value: String) {
        mPreferences.edit().putString(mContext.resources.getString(prefName), value).apply()
    }

    fun setString(prefName: String?, value: String) {
        mPreferences.edit().putString(prefName, value).apply()
    }

    fun getInteger(prefName: Int): Int {
        return mPreferences.getInt(mContext.resources.getString(prefName), 0)
    }

    fun getInteger(prefName: String, defaultValue: Int): Int {
        return mPreferences.getInt(prefName, defaultValue)
    }

    fun setInteger(prefName: String, value: Int) {
        mPreferences.edit().putInt(prefName, value).apply()
    }

    fun getLong(prefName: Int): Long {
        return mPreferences.getLong(mContext.resources.getString(prefName), 0L)
    }

    fun getLong(prefName: Int, defaultValue: Long): Long {
        return mPreferences.getLong(mContext.resources.getString(prefName), defaultValue)
    }

    fun setLong(prefName: Int, value: Long) {
        mPreferences.edit().putLong(mContext.resources.getString(prefName), value).apply()
    }

    fun clearAllPreferences() {
        mPreferences.edit().clear().apply()
    }

}