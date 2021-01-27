package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickAddLicenses
import com.nala.businesslogic.interfaces.OnClickMassageCharges
import com.nala.businesslogic.pojo.PojoAddLicenses
import com.nala.businesslogic.pojo.PojoMassageCharges
import com.nala.databinding.ActivityAddLicensesBinding
import com.nala.databinding.ActivityAddLicensesRowBinding
import com.nala.databinding.ActivityMassageChargesRowBinding

class AdapterAddLicense(
    var mContext: Context, var mArrayContent: List<PojoAddLicenses>,
    var mOnClickAddLicensesListener: OnClickAddLicenses
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mViewTypeItem = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == mViewTypeItem) {
            val binding: ActivityAddLicensesRowBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.activity_add_licenses_row,
                parent,
                false
            )

            binding.setOnContentClickListener(mOnClickAddLicensesListener)

            Viewholder(binding)

        } else {
            ProgressViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_progress, parent, false)
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Viewholder) {
            (holder as Viewholder).bind(mArrayContent[position], position)
        }
    }

    override fun getItemCount(): Int {
        return mArrayContent.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (mArrayContent!![position] != null) mViewTypeItem else 0
    }

    class ProgressViewHolder(v: View?) :
        RecyclerView.ViewHolder(v!!)

    class Viewholder(binding: ActivityAddLicensesRowBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {

        private val mBinding: ActivityAddLicensesRowBinding

        fun bind(data: PojoAddLicenses, position: Int) {
            mBinding.setData(data)
            mBinding.setLayoutPosition(position)
            mBinding.executePendingBindings()

        }

        init {
            mBinding = binding
        }
    }

}