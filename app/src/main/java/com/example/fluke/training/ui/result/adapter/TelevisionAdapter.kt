package com.example.fluke.training.ui.result.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.model.Television
import com.example.fluke.training.ui.result.holder.TelevisionHolder

class TelevisionAdapter(private var arr: List<Television>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(list: List<Television>) = apply {
        arr = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_show_result

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder
            = TelevisionHolder(LayoutInflater.from(parent?.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int)
            = (holder as TelevisionHolder).onBind(arr[position])
}