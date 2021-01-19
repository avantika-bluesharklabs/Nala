package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickMessage
import com.nala.businesslogic.interfaces.OnClickTechnique

import com.nala.businesslogic.pojo.PojoTechnique
import com.nala.databinding.ActivityServiceProRowTechniqueBinding
import com.nala.databinding.FragmentMessageRowBinding

class AdapterTechnique(
    var mContext: Context, var mArrayContent: List<PojoTechnique>,
    var mOnClickTechniqueListener: OnClickTechnique
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mViewTypeItem = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == mViewTypeItem) {
            val binding: ActivityServiceProRowTechniqueBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.activity_service_pro_row_technique,
                parent,
                false
            )



            binding.setOnContentClickListener(mOnClickTechniqueListener)

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

    class Viewholder(binding: ActivityServiceProRowTechniqueBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {

        private val mBinding: ActivityServiceProRowTechniqueBinding

        fun bind(data: PojoTechnique, position: Int) {
            mBinding.setData(data)
            mBinding.setLayoutPosition(position)
            mBinding.executePendingBindings()

        }

        init {
            mBinding = binding
        }
    }

}
