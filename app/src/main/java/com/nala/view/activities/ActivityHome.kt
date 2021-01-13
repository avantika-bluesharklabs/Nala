package com.nala.view.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nala.R
import com.nala.businesslogic.viewmodel.activities.ViewModelHome
import com.nala.databinding.ActivityHomeBinding

class ActivityHome : ActivityBase(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mViewModelHome: ViewModelHome
    private lateinit var mBinding: ActivityHomeBinding
    lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.WHITE
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mViewModelHome = ViewModelHome(mApplication,false)
        mBinding.viewModel = mViewModelHome
        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(this)
        navController = findNavController(R.id.nav_tab_host_fragment)
        mPreferences.setBoolean(R.string.pref_is_user_login, true)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        selectItem(item.itemId, false)
        return true
    }


    private fun selectItem(id: Int, isFromCreate: Boolean): Boolean {

        val menu: Menu = mBinding.bottomNavigation.menu
        val menuItem = menu.findItem(id)
        val isChecked = menuItem.isChecked
        var check = false
        if (isFromCreate) {
            check = true
        } else {
            if (!isChecked) {
                check = true
            }
        }

        if (check) {
            removeBackStack()
            if (id == R.id.navigation_specialist) {
                /* supportActionBar?.title = getString(R.string.nav_specialist)
                 supportActionBar?.hide()*/
                navController.navigate(R.id.navigation_specialist)
            } else if (id == R.id.navigation_booking) {
                navController.navigate(R.id.navigation_booking)
                /*supportActionBar?.title =  getString(R.string.nav_booking)
                supportActionBar?.hide()*/

            } else if (id == R.id.navigation_notification) {
                navController.navigate(R.id.navigation_notification)
                /*supportActionBar?.title = getString(R.string.nav_notification)
                supportActionBar?.hide()*/

            } else if (id == R.id.navigation_chat) {
                navController.navigate(R.id.navigation_chat)
                /*supportActionBar?.title = getString(R.string.nav_favourite)
                supportActionBar?.hide()*/

            } else if (id == R.id.navigation_profile) {
                navController.navigate(R.id.navigation_profile)
                /*supportActionBar?.title = getString(R.string.nav_profile)
                supportActionBar?.hide()*/

            }
        }
        return true
    }

    private fun removeBackStack() {
        val fragmentManager = supportFragmentManager
        for (i in 0 until fragmentManager.backStackEntryCount) {
            fragmentManager.popBackStack()
        }
    }

}