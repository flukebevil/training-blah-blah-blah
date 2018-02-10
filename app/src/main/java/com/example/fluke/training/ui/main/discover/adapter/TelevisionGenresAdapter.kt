package com.example.fluke.training.ui.main.discover.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.ui.main.discover.holder.TelevisionGenresHolder
import com.example.fluke.training.model.TelevisionType

class TelevisionGenresAdapter(private var arr: List<TelevisionType>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(list: List<TelevisionType>) {
        arr = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) = (holder as TelevisionGenresHolder).onBind(arr[position])

    override fun getItemViewType(position: Int): Int = R.layout.item_show_genres

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            TelevisionGenresHolder(LayoutInflater.from(parent?.context).inflate(viewType, parent, false))

}