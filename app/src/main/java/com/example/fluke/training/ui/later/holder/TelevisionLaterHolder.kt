package com.example.fluke.training.ui.later.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.base.BaseUrl
import com.example.fluke.training.load
import com.example.fluke.training.model.Television
import com.example.fluke.training.ui.detail.television.DetailTelevisionActivity
import kotlinx.android.synthetic.main.item_show_fav.view.*

class TelevisionLaterHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {

    fun onBind(movie: Television) {
        itemView.apply {
            ivFav.load(BaseUrl.baseUrlImageMovie + movie.poster_path)
            movieFav.text = movie.name
            setOnClickListener {
                context.startActivity(Intent(context, DetailTelevisionActivity::class.java)
                        .putExtra(DetailTelevisionActivity.MOVIE_KEY, movie))
            }
        }
    }
}