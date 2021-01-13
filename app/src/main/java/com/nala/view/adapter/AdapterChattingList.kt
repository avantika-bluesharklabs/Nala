package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nala.R


class AdapterChattingList(val items: ArrayList<String>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MSG_SEND = 0
        const val VIEW_TYPE_MSG_RECEIVED = 1
    }

    private inner class MSGSendViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

    }

    private inner class MSGReceivedViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

    }

    override fun getItemViewType(position: Int): Int {
//        return items[position].viewType
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_MSG_SEND) {
            return MSGSendViewHolder(
                LayoutInflater.from(context).inflate(R.layout.layout_msg_send, parent, false)
            )
        }
        return MSGReceivedViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_msg_received, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (list[position].viewType === VIEW_TYPE_MSG_SEND) {
//            (holder as MSGSendViewHolder).bind(position)
//        } else {
//            (holder as MSGReceivedViewHolder).bind(position)
//        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return items.size
    }

}
