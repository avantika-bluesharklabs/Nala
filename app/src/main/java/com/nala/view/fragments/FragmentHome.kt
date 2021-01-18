package com.nala.view.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickHomeList
import com.nala.businesslogic.pojo.PojoHomeItemList
import com.nala.businesslogic.viewmodel.fragments.ViewModelHomeList
import com.nala.databinding.FragmentHomeBinding
import com.nala.utils.ConstantCodes
import com.nala.view.activities.ActivityHomeDetails

class FragmentHome : FragmentBase(), OnClickHomeList {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mviewModelHomeList: ViewModelHomeList


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mviewModelHomeList = ViewModelHomeList(mApplication)


        return mBinding.root

    }



    override fun onClickHomeListItem(
        view: View?,
        layoutPosition: Int,
        data: PojoHomeItemList.Data
    ) {
        activity?.startActivity(
            Intent(activity, ActivityHomeDetails::class.java).putExtra(ConstantCodes.INTENT_Therapist_ID, data.id)
        )
    }

}