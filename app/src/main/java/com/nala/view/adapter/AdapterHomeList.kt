package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nala.R

class AdapterHomeList(val items: ArrayList<String>, val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.adapter_home_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
}