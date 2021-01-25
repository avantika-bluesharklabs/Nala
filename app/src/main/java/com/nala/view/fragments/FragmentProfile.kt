package com.nala.view.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.request.RequestOptions
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickMyBooking
import com.nala.businesslogic.interfaces.OnClickProfile
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.viewmodel.fragments.ViewModelMyBookings
import com.nala.businesslogic.viewmodel.fragments.ViewModelProfile
import com.nala.databinding.FragmentMyBookingsBinding
import com.nala.databinding.FragmentProfileBinding
import java.io.ByteArrayOutputStream

class FragmentProfile : FragmentBase(), OnClickProfile {

    private lateinit var mViewModelprofile: ViewModelProfile
    private lateinit var mBinding: FragmentProfileBinding


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        mViewModelprofile = ViewModelProfile(mApplication,false)
        mBinding.vmProfile = mViewModelprofile
        mBinding.onContentClickListener = this

        mBinding.setRequestOptions(mApplication.glideCenterCircle(R.drawable.add_photo))

        mBroadcastManager.registerReceiver(mReceiverImageResult, IntentFilter(resources.getString(R.string.broadcastImageResult)))

        mBinding.imgProfile.setOnClickListener {

           imageChooser()


        }

        return mBinding.root
    }

    override fun onClickProfileItem(view: View?, layoutPosition: Int, data: PojoCommonResponse) {

    }

    private val mReceiverImageResult: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val byteArray =
                intent.getByteArrayExtra(mContext.resources.getString(R.string.bundleImageResultStream))
            val outputStream = ByteArrayOutputStream(byteArray.size)
            outputStream.write(byteArray, 0, byteArray.size)
            mViewModelprofile.observerStreamPic.set(outputStream)
            mViewModelprofile.observerPicUri.set(
                intent.getStringExtra(
                    mContext.resources.getString(
                        R.string.bundleImageResultUri
                    )
                )
            )
        }
    }
}