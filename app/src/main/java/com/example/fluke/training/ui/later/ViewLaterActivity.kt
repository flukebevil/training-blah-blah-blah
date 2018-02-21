package com.example.fluke.training.ui.later

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import com.example.fluke.training.R
import com.example.fluke.training.model.Movie
import com.example.fluke.training.model.MovieList
import com.example.fluke.training.model.Television
import com.example.fluke.training.model.TelevisionList
import com.example.fluke.training.ui.later.adapter.MovieLaterAdapter
import com.example.fluke.training.ui.later.adapter.TelevisionLaterAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_view_my_fav.*

@SuppressLint("Registered")
@Suppress("UNUSED_EXPRESSION")
class ViewLaterActivity : AppCompatActivity() {
    private val laterMovieAdapter: MovieLaterAdapter by lazy { MovieLaterAdapter(arrayListOf())}
    private val laterTvAdapter: TelevisionLaterAdapter by lazy { TelevisionLaterAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_my_fav)
        val show: SharedPreferences = applicationContext.getSharedPreferences("later", Context.MODE_PRIVATE)
        val gson = Gson()
        favMovieList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = laterMovieAdapter
        }
        favTvList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = laterTvAdapter
        }
        Log.e("aeijfa","aiwejfaiw")
        showMovieLaterList(show, gson)
        showTelevisionLaterList(show, gson)
    }

    private fun showTelevisionLaterList(show: SharedPreferences, gson: Gson) {
        val json: String? = show.getString(getString(R.string.sesstion_name), null)
        json?.let {
            val data: TelevisionList = gson.fromJson(it, TelevisionList::class.java)
            val mutationList: MutableList<Television> = data.results as MutableList<Television>
            mutationList.let { it1 -> it1.let { it2 -> laterTvAdapter.setItem(it2) } }
        }
    }

    private fun showMovieLaterList(show: SharedPreferences, gson: Gson) {
        val json: String? = show.getString(getString(R.string.session_name_movie), null)
        json?.let {
            val data: MovieList = gson.fromJson(json, MovieList::class.java)
            val mutationList: MutableList<Movie> = data.results as MutableList<Movie>
            mutationList.let { it1 ->
                it1.let { it2 -> laterMovieAdapter.setItem(it2) }
            }
        }
    }
}