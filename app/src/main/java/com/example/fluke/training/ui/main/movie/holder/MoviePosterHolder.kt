package com.example.fluke.training.ui.main.movie.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.load
import com.example.fluke.training.model.Movie
import com.example.potikorn.testcoopapp.network.BaseUrl
import kotlinx.android.synthetic.main.item_show_poster.view.*

class MoviePosterHolder(view : View) : RecyclerView.ViewHolder(view){

    fun onBind(movie : Movie){
        itemView.apply {
            ivPoster.load(BaseUrl.baseUrlImageMovie+movie.poster)
            movieTitle.text = movie.title
        }
    }
}