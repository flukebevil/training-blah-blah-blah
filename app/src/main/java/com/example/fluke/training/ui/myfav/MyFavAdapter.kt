package com.example.fluke.training.ui.myfav

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import com.example.fluke.training.R
import com.example.fluke.training.model.Movie

class MyFavAdapter(private var arr: List<Movie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    fun setItem(list: List<Movie>) {
        arr = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_show_poster

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder = MyFavHolder(LayoutInflater.from(parent?.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = arr.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) = (holder as MyFavHolder).onBind(arr[position])
}