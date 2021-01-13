package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nala.R

class AdapterHomeList(val items: ArrayList<String>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_home_list, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return items.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}

