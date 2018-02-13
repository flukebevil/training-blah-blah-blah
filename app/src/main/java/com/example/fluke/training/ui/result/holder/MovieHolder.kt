package com.example.fluke.training.ui.result.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.base.BaseUrl
import com.example.fluke.training.load
import com.example.fluke.training.model.Movie
import com.example.fluke.training.ui.detail.movie.DetailMovieActivity
import kotlinx.android.synthetic.main.item_show_result.view.*

class MovieHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun onBind(result: Movie) {
        itemView.apply {
            tvTitle.text = result.title
            ivResult.load(BaseUrl.baseUrlImageMovie + result.poster)
            setOnClickListener {
                context.startActivity(Intent(context, DetailMovieActivity::class.java)
                        .putExtra(DetailMovieActivity.MOVIE_KEY, result))
            }
        }
    }
}