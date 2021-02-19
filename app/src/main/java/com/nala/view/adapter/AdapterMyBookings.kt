package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickMyBooking
import com.nala.businesslogic.pojo.PojoMyBooking
import com.nala.databinding.FragmentMyBookingsRowBinding
import com.nala.view.fragments.FragmentBookingInfo

class AdapterMyBookings(
    var mContext: Context, var mArrayContent: List<PojoMyBooking>,
    var mOnClickmyBookingListener: OnClickMyBooking
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mViewTypeItem = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == mViewTypeItem) {
            val binding: FragmentMyBookingsRowBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_my_bookings_row,
                parent,
                false
            )

            binding.setOnContentClickListener(mOnClickmyBookingListener)

           /* binding.btnCancel.setOnClickListener {


            }*/

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

    class Viewholder(binding: FragmentMyBookingsRowBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {

        private val mBinding: FragmentMyBookingsRowBinding

        fun bind(data: PojoMyBooking, position: Int) {
            mBinding.setData(data)
            mBinding.setLayoutPosition(position)
            mBinding.executePendingBindings()

        }

        init {
            mBinding = binding
        }
    }

}
