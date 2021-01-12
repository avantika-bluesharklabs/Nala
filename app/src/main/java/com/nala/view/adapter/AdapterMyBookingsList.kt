package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nala.R

class AdapterMyBookingsList(val items: ArrayList<String>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.adapter_my_bookings_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}


class Viewholder(view: View) : RecyclerView.ViewHolder(view) {


}
