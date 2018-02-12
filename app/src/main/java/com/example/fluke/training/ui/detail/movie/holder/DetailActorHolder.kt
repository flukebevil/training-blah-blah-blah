package com.example.fluke.training.ui.detail.movie.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.fluke.training.load
import com.example.fluke.training.model.CreditActor
import com.example.fluke.training.base.BaseUrl
import kotlinx.android.synthetic.main.item_actor_crew.view.*

class DetailActorHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun onBind(creditList: CreditActor) {
        itemView.apply {
            tvHuman.text = creditList.cast_nik_name
            ivHuman.load(BaseUrl.baseUrlImageMovie+creditList.cast_pic)
        }
    }
}