package com.example.fluke.training.ui.detail.movie.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.model.Crew
import com.example.fluke.training.ui.detail.movie.holder.DetailCrewHolder

class DetailCrewAdapter(private var arr: List<Crew>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(list: List<Crew>) {
        arr = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_actor_crew

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder
            = DetailCrewHolder(LayoutInflater.from(parent?.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int)
            = (holder as DetailCrewHolder).onBind(arr[position])
}