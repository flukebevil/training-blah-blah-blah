@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.example.fluke.training

import android.util.Log
import android.widget.ImageView
import com.example.fluke.training.model.Movie
import com.example.fluke.training.model.Television

infix fun ImageView.load(url: String?) = this.apply {
    com.bumptech.glide.Glide.with(context).load(url).into(this)
}

fun <T> MutableList<T>.checkNonDuplicate(ex: T): Int {
    if (ex is Movie) {
        val list = this as MutableList<Movie>
        list.forEach {
            if (it.id == ex.id){
                return  0
            }
        }
    }

    if (ex is Television) {
        Log.e("round2 " , "this")
        val list = this as MutableList<Television>
        list.forEach {
             if (it.id == ex.id) {
                 return 0
             }
        }
    }

    return 1
}