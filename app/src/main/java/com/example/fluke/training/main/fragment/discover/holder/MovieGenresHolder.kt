package com.example.fluke.training.main.fragment.discover.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.model.MovieType
import kotlinx.android.synthetic.main.item_show_genres.view.*

class MovieGenresHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun onBind(movie : MovieType){
        itemView.tvGenres.text = movie.type_name
    }
}