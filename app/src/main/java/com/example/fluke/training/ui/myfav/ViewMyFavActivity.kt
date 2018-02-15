package com.example.fluke.training.ui.myfav

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.example.fluke.training.R
import com.example.fluke.training.model.Movie
import com.example.fluke.training.model.MovieList
import com.example.fluke.training.model.Television
import com.example.fluke.training.model.TelevisionList
import com.example.fluke.training.ui.myfav.adapter.MyFavMovieAdapter
import com.example.fluke.training.ui.myfav.adapter.MyFavTvAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_view_my_fav.*

@SuppressLint("Registered")
@Suppress("UNUSED_EXPRESSION")
class ViewMyFavActivity : AppCompatActivity() {
    private val myFavMovieAdapter: MyFavMovieAdapter by lazy { MyFavMovieAdapter(arrayListOf()) }
    private val myFavTvAdapter: MyFavTvAdapter by lazy { MyFavTvAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_my_fav)
        favMovieList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = myFavMovieAdapter
        }
        favTvList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = myFavTvAdapter
        }
        showMovieFavList()
        showTelevisionFavList()
    }

    private fun showTelevisionFavList() {
        val gson = Gson()
         val show: SharedPreferences = applicationContext.getSharedPreferences(getString(R.string.session), Context.MODE_PRIVATE)
        val jsonTv: String? = show.getString(getString(R.string.sesstion_name), null)
        jsonTv?.let {
            val dataTv: TelevisionList = gson.fromJson(it, TelevisionList::class.java)
            val mutationTvList: MutableList<Television> = dataTv.results as MutableList<Television>
            mutationTvList.let { it -> it.let { it1 -> myFavTvAdapter.setItem(it1) } }
        }

    }

    private fun showMovieFavList() {
        val gson = Gson()
         val show: SharedPreferences = applicationContext.getSharedPreferences(getString(R.string.session), Context.MODE_PRIVATE)
        val json: String? = show.getString(getString(R.string.session_name_movie), null)
        json?.let {
            val data: MovieList = gson.fromJson(it, MovieList::class.java)
            val mutationList: MutableList<Movie> = data.results as MutableList<Movie>
            mutationList.let { it -> it.let { it1 -> myFavMovieAdapter.setItem(it1) } }
        }

    }
}