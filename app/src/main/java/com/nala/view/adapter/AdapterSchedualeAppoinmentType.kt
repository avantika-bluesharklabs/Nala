package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickSchedualeAppoinment
import com.nala.businesslogic.interfaces.OnClickSchedualeAppoinmentType
import com.nala.businesslogic.pojo.PojoSchedualeAppoinment
import com.nala.businesslogic.pojo.PojoSchedualeAppoinmentType
import com.nala.databinding.ActivityScheduleAppointmentRowTimeBinding
import com.nala.databinding.ActivityScheduleAppointmentRowTypeBinding

class AdapterSchedualeAppoinmentType(
    var mContext: Context, var mArrayContent: List<PojoSchedualeAppoinmentType>,
    var mOnClickSchedualeAppoinmentTypeListener: OnClickSchedualeAppoinmentType
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mViewTypeItem = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == mViewTypeItem) {
            val binding: ActivityScheduleAppointmentRowTypeBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.activity_schedule_appointment_row_type,
                parent,
                false
            )

            // binding.onContentClickListener = mOnClickHomeListListener

            binding.setOnContentClickListener(mOnClickSchedualeAppoinmentTypeListener)

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

    class Viewholder(binding: ActivityScheduleAppointmentRowTypeBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {

        private val mBinding: ActivityScheduleAppointmentRowTypeBinding

        fun bind(data: PojoSchedualeAppoinmentType, position: Int) {
            mBinding.setData(data)
            mBinding.setLayoutPosition(position)
            mBinding.executePendingBindings()

        }

        init {
            mBinding = binding
        }
    }

}
