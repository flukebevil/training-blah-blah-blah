package com.example.fluke.training.ui.result.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.base.BaseUrl
import com.example.fluke.training.load
import com.example.fluke.training.model.Television
import com.example.fluke.training.ui.detail.television.DetailTelevisionActivity
import kotlinx.android.synthetic.main.item_show_result.view.*

class TelevisionHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun onBind(result: Television) {
        itemView.apply {
            tvTitle.text = result.name
            ivResult.load(BaseUrl.baseUrlImageMovie + result.poster_path)
            setOnClickListener {
                context.startActivity(Intent(context, DetailTelevisionActivity::class.java)
                        .putExtra(DetailTelevisionActivity.MOVIE_KEY, result))
            }
        }
    }
}