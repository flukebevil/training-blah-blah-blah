package com.example.fluke.training.main.fragment.discover.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.model.TelevisionType
import kotlinx.android.synthetic.main.item_show_genres.view.*

class TelevisionGenresHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    fun onBind(television : TelevisionType){
        itemView.tvGenres.text = television.type_name
    }
}