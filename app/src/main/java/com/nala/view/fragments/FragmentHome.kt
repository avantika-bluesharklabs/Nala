package com.nala.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickHome
import com.nala.businesslogic.pojo.PojoHome
import com.nala.businesslogic.viewmodel.activities.ViewModelHome
import com.nala.databinding.FragmentHomeBinding
import com.nala.view.activities.ActivityHomeDetails

class FragmentHome : FragmentBase(), OnClickHome {

    private lateinit var mViewModelHome: ViewModelHome
    private lateinit var mBinding: FragmentHomeBinding

    var par:Int? =null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        mViewModelHome = ViewModelHome(mApplication,false)
        mBinding.vmHomeList = mViewModelHome
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

        val btn_book: AppCompatButton? = view?.findViewById(R.id.btn_book_now_f)

        btn_book?.setOnClickListener {   startActivity(Intent(mContext, ActivityHomeDetails::class.java)) }

    }

    companion object {

        fun newInstance() =
            FragmentHome().apply {

            }
    }
}