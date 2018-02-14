package com.example.fluke.training.ui.myfav.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.base.BaseUrl
import com.example.fluke.training.load
import com.example.fluke.training.model.Movie
import com.example.fluke.training.ui.detail.movie.DetailMovieActivity
import kotlinx.android.synthetic.main.item_show_fav.view.*

@Suppress("DEPRECATION")
class MyFavMovieHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun onBind(movie: Movie) {
        itemView.apply {
            ivFav.load(BaseUrl.baseUrlImageMovie + movie.poster)
            movieFav.text = movie.title
            setOnClickListener {
                context.startActivity(Intent(context, DetailMovieActivity::class.java).putExtra(DetailMovieActivity.MOVIE_KEY, movie))
            }
        }
    }
}