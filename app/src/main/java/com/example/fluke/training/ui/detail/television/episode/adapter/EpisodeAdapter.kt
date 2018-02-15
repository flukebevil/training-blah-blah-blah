package com.example.fluke.training.ui.detail.television.episode.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.model.Episode
import com.example.fluke.training.ui.detail.television.episode.holder.EpisodeHolder

class EpisodeAdapter(private var arr : List<Episode>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun setItem(list : List<Episode>){
        arr = list
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int = R.layout.item_episode

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder
            =EpisodeHolder(LayoutInflater.from(parent?.context).inflate(viewType , parent , false))

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int)
            =(holder as EpisodeHolder).onBind(arr[position])
}