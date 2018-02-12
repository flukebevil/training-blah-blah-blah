package com.example.fluke.training.ui.detail.movie.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.model.CreditActor
import com.example.fluke.training.ui.detail.movie.holder.DetailActorHolder

class DetailActorAdapter(private var arr: List<CreditActor>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(array: List<CreditActor>) {
        arr = array
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_actor_crew

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder = DetailActorHolder(LayoutInflater.from(parent?.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int)
            = (holder as DetailActorHolder).onBind(arr[position])

}