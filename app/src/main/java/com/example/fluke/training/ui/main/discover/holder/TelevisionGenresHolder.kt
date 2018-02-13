package com.example.fluke.training.ui.main.discover.holder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.model.TelevisionType
import com.example.fluke.training.ui.main.discover.DiscoverFragment
import com.example.fluke.training.ui.result.ResultActivity
import kotlinx.android.synthetic.main.item_show_genres.view.*

class TelevisionGenresHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun onBind(television: TelevisionType) {
        itemView.apply {
            tvGenres.text = television.type_name
            setOnClickListener {
                context.startActivity(Intent(context, ResultActivity::class.java)
                        .putExtra(DiscoverFragment.KEY_TELEVISION, television.type_id))
            }
        }

    }
}