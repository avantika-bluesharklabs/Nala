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
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickProfile
import com.nala.businesslogic.pojo.PojoCommonResponse
import com.nala.businesslogic.viewmodel.fragments.ViewModelProfile
import com.nala.databinding.FragmentProfileBinding
import com.nala.view.activities.ActivityAddLicenses
import com.nala.view.activities.ActivityMassageCharges
import com.nala.view.activities.ActivityMassageTechnique
import com.nala.view.activities.ActivityWeeklyScheduale
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

        mBinding.constMsgTcnq.setOnClickListener {

            val i = Intent(activity, ActivityMassageTechnique::class.java)
            getActivity()?.startActivity(i)

        }

        mBinding.constMasageCharge.setOnClickListener {

            val i = Intent(activity, ActivityMassageCharges::class.java)
            getActivity()?.startActivity(i)

        }

        mBinding.constMassageLicense.setOnClickListener {

            val i = Intent(activity, ActivityAddLicenses::class.java)
            getActivity()?.startActivity(i)

        }

        mBinding.constWeeklySchedule.setOnClickListener {

            val i = Intent(activity, ActivityWeeklyScheduale::class.java)
            getActivity()?.startActivity(i)

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