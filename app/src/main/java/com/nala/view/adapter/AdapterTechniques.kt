package com.nala.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nala.R

class AdapterTechniques(val items: ArrayList<String>, val context: Context) :
    RecyclerView.Adapter<TechViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechViewHolder {
        return TechViewHolder(
            LayoutInflater.from(context).inflate(R.layout.adapter_techniques, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TechViewHolder, position: Int) {

    }
}

class TechViewHolder(view: View) : RecyclerView.ViewHolder(view) {
}