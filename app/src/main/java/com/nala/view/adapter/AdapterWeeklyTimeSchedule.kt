package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickReview
import com.nala.businesslogic.interfaces.OnClickWeeklyTimeSchedule
import com.nala.businesslogic.pojo.PojoReview
import com.nala.businesslogic.pojo.PojoWeeklyTimeSchedule
import com.nala.databinding.ActivityWeeklySchedualeDayRowBinding
import com.nala.databinding.ActivityWeeklySchedualeTimeRowBinding
import com.nala.databinding.FragmentServiceProRowReviewBinding

class AdapterWeeklyTimeSchedule (
    var mContext: Context, var mArrayContent: List<PojoWeeklyTimeSchedule>,
    var mOnClickWeeklyTimeScheduleListener: OnClickWeeklyTimeSchedule
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mViewTypeItem = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == mViewTypeItem) {
            val binding: ActivityWeeklySchedualeTimeRowBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.activity_weekly_scheduale_time_row, parent, false)

            binding.onContentClickTimeListener = mOnClickWeeklyTimeScheduleListener

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

    class Viewholder(binding: ActivityWeeklySchedualeTimeRowBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {

        private val mBinding: ActivityWeeklySchedualeTimeRowBinding

        fun bind(data: PojoWeeklyTimeSchedule, position: Int) {
            mBinding.setData(data)
            mBinding.setLayoutPosition(position)
            mBinding.executePendingBindings()

        }

        init {
            mBinding = binding
        }
    }

}
