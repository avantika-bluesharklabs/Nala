package com.nala.view.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
import java.util.*


class FragmentHome : FragmentBase(), OnClickHome, OnMapReadyCallback {

    private lateinit var mViewModelHome: ViewModelHome
    private lateinit var mViewModelHomeMap: ViewModelHomeMap
    private lateinit var mBinding: FragmentHomeBinding

    private var date: String? = null

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
        getLocation()



        mBinding.imgFilter.setOnClickListener {




            val view: View = layoutInflater.inflate(R.layout.layout_my_booking_filters, null)
            val dialog = BottomSheetDialog(mContext, R.style.BottomSheetDialogTheme) // Style here

            dialog.setContentView(view)
            dialog.show()






            /* img_date.setOnClickListener {

                 val c = Calendar.getInstance()
                 val year = c.get(Calendar.YEAR)
                 val month = c.get(Calendar.MONTH)
                 val day = c.get(Calendar.DAY_OF_MONTH)



                 val datePickerDialog = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                     txt_select.setText(year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString())

                     date = year.toString() + "-" + (monthOfYear + 1) + "-" + dayOfMonth.toString()

                 }, year, month, day
                 )
                 datePickerDialog.show()

             }*/


        }


        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)



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


        googleMap?.getUiSettings()?.setMyLocationButtonEnabled(false);
       // googleMap?.isMyLocationEnabled = true




        val sydney = LatLng((-34).toDouble(), 151.0)
        googleMap?.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))

    }


    private val mReceiverLocationResult: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val latitude = intent.getDoubleExtra(
                mContext.resources.getString(R.string.bundleLocationLatitude),
                0.0
            )
            val longitude = intent.getDoubleExtra(
                mContext.resources.getString(R.string.bundleLocationLongitude),
                0.0
            )



            mViewModelHomeMap.user_lat = latitude

            mViewModelHomeMap.user_long = longitude

            val geocoder = Geocoder(mContext, Locale.getDefault())
            val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)
            val cityName: String =addresses[0].getAddressLine(0)
           // val stateName: String = addresses[0].getAddressLine(1)
                //   val countryName: String = addresses[0].getAddressLine(2)

            mViewModelHomeMap.city_name = cityName
            mViewModelHomeMap.observerCityName.set(cityName)

            Log.d("TAG", "latitude" + latitude)
            Log.d("TAG", "longitude" + longitude)
            Log.d("TAG", "cityName" +  mViewModelHomeMap.city_name)
        //    Log.d("TAG", "stateName" +  addresses[0].getAddressLine(1))
         //   Log.d("TAG", "countryName" +  addresses[0].getAddressLine(2))



          //  mViewModelHomeMap.networkCallList()


       //     Log.e("MYTAGS", "onLocationChanged" + "Latitudee: " + latitude + "   Longitude: " + longitude)

        }
    }


}

