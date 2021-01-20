package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickNotification
import com.nala.businesslogic.pojo.PojoNotisfication
import com.nala.businesslogic.viewmodel.fragments.ViewModelHomeMap
import com.nala.businesslogic.viewmodel.fragments.ViewModelMyBookings
import com.nala.businesslogic.viewmodel.fragments.ViewModelNotisfication
import com.nala.databinding.FragmentHomeBinding
import com.nala.databinding.FragmentNotificationBinding

class FragmentNotification : FragmentBase(),OnClickNotification {

    private lateinit var mViewModelNotisfication: ViewModelNotisfication
    private lateinit var mBinding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)

        mViewModelNotisfication = ViewModelNotisfication(mApplication,false)
        mBinding.vmNotification = mViewModelNotisfication
        mBinding.onContentClickListener = this



        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onClickNotificationItem(view: View?, layoutPosition: Int, data: PojoNotisfication) {



    }

}