package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickMassageCharges
import com.nala.businesslogic.interfaces.OnClickMyBooking
import com.nala.businesslogic.pojo.PojoMassageCharges
import com.nala.businesslogic.pojo.PojoMyBooking
import com.nala.databinding.ActivityMassageChargesRowBinding
import com.nala.databinding.FragmentMyBookingsRowBinding

class AdapterMassageCharges(
    var mContext: Context, var mArrayContent: List<PojoMassageCharges>,
    var mOnClickMassageChargesListener: OnClickMassageCharges
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mViewTypeItem = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == mViewTypeItem) {
            val binding: ActivityMassageChargesRowBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.activity_massage_charges_row,
                parent,
                false
            )

            binding.setOnContentClickListener(mOnClickMassageChargesListener)

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

    class Viewholder(binding: ActivityMassageChargesRowBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {

        private val mBinding: ActivityMassageChargesRowBinding

        fun bind(data: PojoMassageCharges, position: Int) {
            mBinding.setData(data)
            mBinding.setLayoutPosition(position)
            mBinding.executePendingBindings()

        }

        init {
            mBinding = binding
        }
    }

}