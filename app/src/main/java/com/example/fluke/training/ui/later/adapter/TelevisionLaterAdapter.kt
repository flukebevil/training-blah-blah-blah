package com.example.fluke.training.ui.later.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.model.Television
import com.example.fluke.training.model.TelevisionList
import com.example.fluke.training.ui.later.holder.TelevisionLaterHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.item_show_fav.view.*

class TelevisionLaterAdapter(private var arr: MutableList<Television>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(list: MutableList<Television>) {
        arr = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_show_fav

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder = TelevisionLaterHolder(LayoutInflater.from(parent?.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as TelevisionLaterHolder).onBind(arr[position])
        holder.itemView.btnDelete.setOnClickListener {
            onDelete(position, holder.itemView)
        }
    }

    @SuppressLint("CommitPrefEdits", "ApplySharedPref")
    private fun onDelete(position: Int, itemView: View) {
        val gson = Gson()
        val show: SharedPreferences = itemView.context.getSharedPreferences("later", Context.MODE_PRIVATE)
        val json: String? = show.getString("tv", "")
        val type = object : TypeToken<TelevisionList>() {}.type
        val mu: TelevisionList? = gson.fromJson(json, type)
        val mutationList: MutableList<Television> = mu?.results as MutableList<Television>
        val editFav: SharedPreferences.Editor = show.edit()
        mutationList.removeAt(position)
        val ml = TelevisionList(mutationList)
        val convert: String = gson.toJson(ml)
        editFav.putString("tv", convert)
        editFav.commit()
        arr.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, arr.size)
    }

}