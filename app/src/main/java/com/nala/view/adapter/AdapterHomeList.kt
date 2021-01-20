package com.nala.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nala.R
import com.nala.businesslogic.interfaces.OnClickHome
import com.nala.businesslogic.pojo.PojoHome
import com.nala.databinding.FragmentHomeRowListBinding
import com.nala.view.activities.ActivityServicePro

class AdapterHomeList(
    var mContext: Context, var mArrayContent: List<PojoHome>,
    var mOnClickHomeListener: OnClickHome
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mViewTypeItem = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == mViewTypeItem) {
            val binding: FragmentHomeRowListBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_home_row_list,
                parent,
                false
            )

            // binding.onContentClickListener = mOnClickHomeListListener

            binding.setOnContentClickListener(mOnClickHomeListener)

           binding.btnBookNowF.setOnClickListener {

               val intent = Intent(mContext, ActivityServicePro::class.java)

               mContext.startActivity(intent)



           }

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

    class Viewholder(binding: FragmentHomeRowListBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {

        private val mBinding: FragmentHomeRowListBinding

        fun bind(data: PojoHome, position: Int) {
            mBinding.setData(data)
            mBinding.setLayoutPosition(position)
            mBinding.executePendingBindings()

        }

        init {
            mBinding = binding
        }
    }

}
