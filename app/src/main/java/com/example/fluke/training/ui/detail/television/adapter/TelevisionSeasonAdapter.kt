package com.example.fluke.training.ui.detail.television.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.ui.detail.television.holder.SeasonViewHolder

class TelevisionSeasonAdapter(private var arr :List<String> , private var id: String?=null) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun setItem(seasonList: List<String>, tvId: String){
        arr = seasonList
        id = tvId
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int = R.layout.item_season

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder
            =SeasonViewHolder(LayoutInflater.from(parent?.context).inflate(viewType,parent, false))

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int)
            =(holder as SeasonViewHolder).onBind(arr[position],id)
}