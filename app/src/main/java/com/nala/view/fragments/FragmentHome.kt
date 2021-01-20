package com.nala.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickHome
import com.nala.businesslogic.pojo.PojoHome
import com.nala.businesslogic.viewmodel.fragments.ViewModelHome
import com.nala.businesslogic.viewmodel.fragments.ViewModelHomeMap
import com.nala.databinding.FragmentHomeBinding

class FragmentHome : FragmentBase(), OnClickHome {

    private lateinit var mViewModelHome: ViewModelHome
    private lateinit var mViewModelHomeMap: ViewModelHomeMap
    private lateinit var mBinding: FragmentHomeBinding





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


}