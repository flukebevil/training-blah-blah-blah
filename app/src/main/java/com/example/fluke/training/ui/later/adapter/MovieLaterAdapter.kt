package com.example.fluke.training.ui.later.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.model.Movie
import com.example.fluke.training.model.MovieList
import com.example.fluke.training.ui.later.holder.MovieLaterHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.item_show_fav.view.*

class MovieLaterAdapter(private var arr: MutableList<Movie>)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setItem(list: MutableList<Movie>) {
        arr = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_show_fav

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder = MovieLaterHolder(LayoutInflater.from(parent?.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as MovieLaterHolder).onBind(arr[position])
        holder.itemView.btnDelete.setOnClickListener {
            onDelete(position, holder.itemView)
        }
    }

    @SuppressLint("CommitPrefEdits", "ApplySharedPref")
    private fun onDelete(position: Int, itemView: View) {
        val gson = Gson()
        val show: SharedPreferences = itemView.context.getSharedPreferences("later", Context.MODE_PRIVATE)
        val json: String? = show.getString("movie", "")
        val type = object : TypeToken<MovieList>() {}.type
        val mu: MovieList? = gson.fromJson(json, type)
        val mutationList: MutableList<Movie> = mu?.results as MutableList<Movie>
        val editFav: SharedPreferences.Editor = show.edit()
        mutationList.removeAt(position)
        val ml = MovieList(mutationList)
        val convert: String = gson.toJson(ml)
        editFav.putString("movie", convert)
        editFav.commit()
        arr.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, arr.size)
    }
}