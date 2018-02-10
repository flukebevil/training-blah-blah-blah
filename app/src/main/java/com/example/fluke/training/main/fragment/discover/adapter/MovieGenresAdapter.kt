package com.example.fluke.training.main.fragment.discover.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.main.fragment.discover.holder.MovieGenresHolder
import com.example.fluke.training.model.MovieType


class MovieGenresAdapter(private var arr: List<MovieType>)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(list : List<MovieType>) {
        arr = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_show_genres

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder = MovieGenresHolder(LayoutInflater.from(parent?.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) =
            (holder as MovieGenresHolder).onBind(arr[position])

}