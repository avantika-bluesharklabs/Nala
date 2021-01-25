package com.nala.view.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nala.R

import com.nala.businesslogic.interfaces.OnClickHome
import com.nala.businesslogic.pojo.PojoHome
import com.nala.businesslogic.viewmodel.fragments.ViewModelHome
import com.nala.businesslogic.viewmodel.fragments.ViewModelHomeMap
import com.nala.databinding.FragmentHomeBinding


class FragmentHome : FragmentBase(), OnClickHome, OnMapReadyCallback {

    private lateinit var mViewModelHome: ViewModelHome
    private lateinit var mViewModelHomeMap: ViewModelHomeMap
    private lateinit var mBinding: FragmentHomeBinding

    var mapFragment: SupportMapFragment? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        mViewModelHome = ViewModelHome(mApplication, false)
        mViewModelHomeMap = ViewModelHomeMap(mApplication, false)
        mBinding.vmHomeList = mViewModelHome
        mBinding.vmHomeMap = mViewModelHomeMap
        mBinding.onContentClickListener = this

        mBroadcastManager.registerReceiver(mReceiverLocationResult, IntentFilter(resources.getString(R.string.broadcastLocationResult)))


        mBinding.imgFilter.setOnClickListener {

            //FragmentBottomsheetFilter().show(mActivity.getSupportFragmentManager(), "Dialog")

            val dialog = BottomSheetDialog(mContext)
            val bottomSheet = layoutInflater.inflate(R.layout.layout_my_booking_filters, null)

           // val img_cross:AppCompatImageView = findviewById

             //  bottomSheet.buttonSubmit.setOnClickListener { dialog.dismiss() }

            dialog.setContentView(bottomSheet)
            dialog.show()
        }


      /*  mapFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment?.getMapAsync(this)*/




        mBinding.rvHomeList.visibility = View.VISIBLE



        mBinding.imgUsers.setOnClickListener {

            mBinding.rvHomeList.visibility = View.GONE
            mBinding.clConstraint.visibility = View.VISIBLE


        }

        mBinding.imgMap.setOnClickListener {

            mBinding.rvHomeList.visibility = View.VISIBLE
            mBinding.clConstraint.visibility = View.GONE


        }



        return mBinding.root

    }

    override fun onClickHomeItem(view: View?, layoutPosition: Int, data: PojoHome) {

        /*  val btn_book: AppCompatButton? = view?.findViewById(R.id.btn_book_now_f)

          btn_book?.setOnClickListener {   startActivity(Intent(mContext, ActivityHomeDetails::class.java)) }*/

    }

    override fun onClickHomeBookNow(view: View?, layoutPosition: Int, data: PojoHome) {

        val fragment: Fragment = FragmentServicePro()
        mMainActivity?.addFragment(fragment, "FragmentServicePro", "FragmentServicePro")

    }

    override fun onClickHomeHeart(view: View?, layoutPosition: Int, data: PojoHome) {

    }

    override fun onMapReady(googleMap: GoogleMap?) {


        val sydney = LatLng((-34).toDouble(), 151.0)
        googleMap?.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))

    }


    private val mReceiverLocationResult: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val latitude = intent.getDoubleExtra(mContext.resources.getString(R.string.bundleLocationLatitude),0.0)
            val longitude = intent.getDoubleExtra(mContext.resources.getString(R.string.bundleLocationLongitude),0.0)



            mViewModelHomeMap.user_lat = latitude
            mViewModelHomeMap.user_long = longitude

//
//            mViewModelWeatherForcast.user_lat = 36.7783
//            mViewModelWeatherForcast.user_long = 119.4179

            mViewModelHomeMap.networkCallList()


            Log.e(
                "MYTAGS",
                "onLocationChanged" + "Latitudee: " + latitude+ "   Longitude: " + longitude)

        }
    }


}

