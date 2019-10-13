package com.vicky.apps.datapoints.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.ui.model.DetailModel

class DetailListAdapter constructor(var data: List<DetailModel>) : RecyclerView.Adapter<DetailListAdapter.DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):DataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.detail_child_view,parent,false)
        return DataViewHolder(v)

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.key.text = data[position].key
        holder.value.text = data[position].value
    }


    class DataViewHolder(v: View): RecyclerView.ViewHolder(v){
        val key: TextView = v.findViewById(R.id.keyText)
        val value: TextView = v.findViewById(R.id.valueText)
    }
}