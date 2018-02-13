package com.example.fluke.training.ui.detail.movie

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseActivity
import com.example.fluke.training.base.BaseUrl
import com.example.fluke.training.detail.DetailContract
import com.example.fluke.training.detail.movie.DetailPresenter
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.load
import com.example.fluke.training.model.*
import com.example.fluke.training.ui.detail.movie.adapter.DetailActorAdapter
import com.example.fluke.training.ui.detail.movie.adapter.DetailCrewAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_detail.*

@SuppressLint("Registered")
class DetailMovieActivity : BaseActivity<DetailContract.View, DetailPresenter>(), DetailContract.View {

    override fun getVideo(moviePath: MovieVideoPath) {
        applicationContext.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BaseUrl.baseYouUrl + moviePath.key)))
    }

    private val adapterActor: DetailActorAdapter by lazy { DetailActorAdapter(arrayListOf()) }
    private val adapterCrew: DetailCrewAdapter by lazy { DetailCrewAdapter(arrayListOf()) }

    override fun getViewCrew(arr: List<Crew>?) {
        arr?.let { adapterCrew.setItem(it) }
    }

    override fun getViewActor(arr: List<CreditActor>?) {
        arr?.let { adapterActor.setItem(it) }
    }

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun layoutContentView(): Int = R.layout.activity_detail

    override fun setupView() {
        val ex = intent.getParcelableExtra<Movie>(MOVIE_KEY)
        tvName.text = ex.title
        tvDesc.text = ex.overview
        ivBackdrop.apply {
            load(BaseUrl.baseUrlImageMovie + ex.backdrop)
            setOnClickListener { presenter.getVideoPath(ex.id) }
        }
        presenter.apply {
            getDataActor(ex.id)
            getDataCrew(ex.id)
        }
        val saveFav: SharedPreferences = applicationContext.getSharedPreferences("myfav", Context.MODE_PRIVATE)
        val editFav: SharedPreferences.Editor = saveFav.edit()
        val gson = Gson()
        btnFav.setOnClickListener {
            favManager(ex, editFav, gson)
        }
        setAdapter()
    }

    @SuppressLint("ApplySharedPref")
    private fun favManager(ex: Movie, editFav: SharedPreferences.Editor, gson: Gson) {
        val mutableList: MutableList<Movie>
        val show: SharedPreferences = applicationContext.getSharedPreferences("myfav", Context.MODE_PRIVATE)
        val json: String? = show.getString("movie", "")
        val type = object : TypeToken<MovieList>() {}.type
        val mu: MovieList? = gson.fromJson(json, type)
        var movieList = MovieList(arrayListOf())//Log.e("OK", mu.toString())
        mu?.let { it -> movieList = it }
        mutableList = movieList.results as MutableList<Movie>
        mutableList.add(ex)
        val ml = MovieList(mutableList)
        val convert: String = gson.toJson(ml)
        editFav.putString("movie", convert)
        editFav.commit()//Log.e("check", mutableList.size.toString())
    }

    private fun setAdapter() {
        actorList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = adapterActor
        }
        crewList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = adapterCrew
        }
    }

    companion object {
        const val MOVIE_KEY = "MOVIEABC"
    }
}