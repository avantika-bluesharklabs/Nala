package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nala.R
import com.nala.businesslogic.viewmodel.fragments.ViewModelBookingInfo
import com.nala.databinding.FragmentBookingInfoBinding


class FragmentBookingInfo : FragmentBase(), OnMapReadyCallback {

    private lateinit var mViewModelBookingInfo: ViewModelBookingInfo
    private lateinit var mBinding: FragmentBookingInfoBinding
    private lateinit var mMap: GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_booking_info, container, false)

        mViewModelBookingInfo = ViewModelBookingInfo(mApplication, false)

        mBinding.vmBookingInfo = mViewModelBookingInfo

        observer()


        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_bookimg) as SupportMapFragment

        mapFragment.getMapAsync(this)



        mBinding.cosntRateReview.setOnClickListener {

            val fragment: Fragment = FragmentRateReview()
            mMainActivity?.addFragment(fragment, "FragmentRateReview", "FragmentRateReview")

        }

        return mBinding.root
    }


    fun observer() {

        mViewModelBookingInfo.getEventBack().observe(mActivity) {
            mMainActivity?.onBackPressed()
        }


    }

    override fun onMapReady(googleMap: GoogleMap?) {

        mMap = googleMap!!

        // Add a marker in Sydney and move the camera
        val ahmedabad = LatLng(23.0225, 72.5714)

        mMap.addMarker(MarkerOptions().position(ahmedabad).title("Marker in Ahmedabad"))

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ahmedabad))

    }
}