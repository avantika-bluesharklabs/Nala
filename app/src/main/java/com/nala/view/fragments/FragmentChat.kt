package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickMessage
import com.nala.businesslogic.viewmodel.fragments.ViewModelChat
import com.nala.businesslogic.viewmodel.fragments.ViewModelMessage
import com.nala.databinding.FragmentChatBinding
import com.nala.databinding.FragmentMessageBinding

class FragmentChat : FragmentBase() {

    private lateinit var mViewModelChat: ViewModelChat
    private lateinit var mBinding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)

        mViewModelChat = ViewModelChat(mApplication, false)
        mBinding.vmChat = mViewModelChat


        observer()


        return mBinding.root
    }

    fun observer() {


        mViewModelChat.getEventBack().observe(mActivity) {
            mMainActivity?.onBackPressed()
        }
    }
}