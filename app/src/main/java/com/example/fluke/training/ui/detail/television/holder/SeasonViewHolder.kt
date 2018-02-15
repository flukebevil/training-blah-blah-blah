package com.example.fluke.training.ui.detail.television.holder

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.R
import com.example.fluke.training.ui.detail.television.episode.EpisodeActivity
import kotlinx.android.synthetic.main.item_season.view.*

class SeasonViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun onBind(number: String, id: String?) {
        itemView.apply {
            tvSeasonNumber.text = context.getString(R.string.season) + number
            setOnClickListener {
                context.startActivity(Intent(context, EpisodeActivity::class.java).apply {
                    putExtra(EpisodeActivity.EPISODE_KEY, number)
                    putExtra(EpisodeActivity.TV_ID_KEY, id)
                })
            }
        }
    }
}