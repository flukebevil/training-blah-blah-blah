package com.example.fluke.training.ui.main.television.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.ui.main.movie.holder.MoviePosterHolder
import com.example.fluke.training.model.Movie
import com.example.fluke.training.model.Television
import com.example.fluke.training.ui.main.television.holder.TelevisionPosterHolder

class TelevisionPosterAdapter(private var movieList: List<Television>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(arr: List<Television>) = apply {
        movieList = arr
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = TelevisionPosterHolder(LayoutInflater.from(parent.context).inflate(viewType , parent , false))

    override fun getItemCount(): Int = movieList.size

    override fun getItemViewType(position: Int): Int= R.layout.item_show_poster

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as TelevisionPosterHolder).onBind(movieList[position])
    }

}