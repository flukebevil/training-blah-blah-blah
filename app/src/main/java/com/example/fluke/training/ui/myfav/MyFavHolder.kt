package com.example.fluke.training.ui.myfav

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.base.BaseUrl
import com.example.fluke.training.load
import com.example.fluke.training.model.Movie
import kotlinx.android.synthetic.main.item_show_poster.view.*

class MyFavHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun onBind(movie: Movie) {
        itemView.apply {
            ivPoster.load(BaseUrl.baseUrlImageMovie + movie.poster)
            movieTitle.text = movie.title
        }
    }
}