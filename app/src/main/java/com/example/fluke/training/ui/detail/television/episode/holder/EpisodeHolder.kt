package com.example.fluke.training.ui.detail.television.episode.holder

import android.support.v7.widget.RecyclerView
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.example.fluke.training.base.BaseUrl
import com.example.fluke.training.load
import com.example.fluke.training.model.Episode
import kotlinx.android.synthetic.main.item_episode.view.*

class EpisodeHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    fun onBind(episode : Episode){
        itemView.apply {
            ivEpisode.load(BaseUrl.baseUrlImageMovie+episode.still_path)
            tvEpisodeTitle.apply {
                movementMethod = ScrollingMovementMethod()
                text = episode.name
            }
            tvEpisodeOverView.apply {
                movementMethod = ScrollingMovementMethod()
                text = episode.overview
            }
            tvEpisodeAirDate.text = episode.air_date
        }
    }
}