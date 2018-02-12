package com.example.fluke.training.ui.detail.movie.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.load
import com.example.fluke.training.model.Crew
import com.example.fluke.training.base.BaseUrl
import kotlinx.android.synthetic.main.item_actor_crew.view.*

class DetailCrewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    fun onBind(crew : Crew){
        itemView.apply {
            tvHuman.text = crew.name
            ivHuman.load(BaseUrl.baseUrlImageMovie+crew.profile_path)
        }
    }
}