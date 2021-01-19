package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickMessage
import com.nala.businesslogic.interfaces.OnClickNotification
import com.nala.businesslogic.pojo.PojoMessage
import com.nala.businesslogic.pojo.PojoNotisfication
import com.nala.businesslogic.viewmodel.fragments.ViewModelMessage
import com.nala.businesslogic.viewmodel.fragments.ViewModelNotisfication
import com.nala.databinding.FragmentMessageBinding
import com.nala.databinding.FragmentNotificationBinding

class FragmentMessage : FragmentBase(), OnClickMessage {

    private lateinit var mViewModelMessage: ViewModelMessage
    private lateinit var mBinding: FragmentMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false)

        mViewModelMessage = ViewModelMessage(mApplication,false)
        mBinding.vmMessage = mViewModelMessage
        mBinding.onContentClickListener = this



        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



    override fun onClickMessageItem(view: View?, layoutPosition: Int, data: PojoMessage) {

    }

}