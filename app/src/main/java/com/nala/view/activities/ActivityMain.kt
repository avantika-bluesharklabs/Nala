package com.nala.view.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelMain
import com.nala.databinding.ActivityMainBinding
import com.nala.utils.Utils
import com.nala.view.fragments.FragmentMain

class ActivityMain : ActivityBase() {


    lateinit var mBinding:ActivityMainBinding
    lateinit var mViewModelMain : ViewModelMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mViewModelMain  = ViewModelMain(mApplication, true)
        mBinding.vmMain = mViewModelMain

        navigateToHome()

        mPreferences.setBoolean(R.string.pref_is_user_login, true)

    }

    fun navigateToHome() {
        val fragment: Fragment = FragmentMain()
        addFragment(fragment, "FragmentMain", "FragmentMain")
    }


    fun addFragment(fragment: Fragment?, title: Int, tag: Int) {
        addFragment(fragment, getString(title), getString(tag))
    }

    fun addFragment(fragment: Fragment?, title: String?, tag: String?) {
        supportFragmentManager.beginTransaction().add(R.id.frame_container, fragment!!, tag)
            .addToBackStack(tag).commit()
    }

    fun replaceFragment(fragment: Fragment?, title: String?, tag: String?) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment!!, tag)
            .addToBackStack(tag).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            fragmentBackPressed()
        } else {
            finish()
        }
    }

    private fun fragmentBackPressed() {
        val view = getCurrentFragment()!!.requireActivity().currentFocus
        if (view != null) {
            Utils.hideKeyboard(this)
        }
        super.onBackPressed()
    }

    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.frame_container)
    }


    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}