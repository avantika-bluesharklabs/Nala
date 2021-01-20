package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nala.R
import com.nala.databinding.FragmentMainBinding

class FragmentMain: FragmentBase() {

    private lateinit var mBinding: FragmentMainBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val navControllerr = childFragmentManager.findFragmentById(R.id.nav_tab_host_fragment)

        navController = navControllerr!!.findNavController()
        mBinding.bottomNavigation.setupWithNavController(navController)
        return mBinding.root
    }


}