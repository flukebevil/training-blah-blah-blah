package com.example.fluke.training.ui.detail.movie

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.widget.Toast
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseActivity
import com.example.fluke.training.base.BaseUrl
import com.example.fluke.training.checkNonDuplicate
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
        val mutableList: MutableList<Movie>
        val show: SharedPreferences = applicationContext.getSharedPreferences(getString(R.string.session), Context.MODE_PRIVATE)
        val json: String? = show.getString(getString(R.string.session_name_movie), "")
        val type = object : TypeToken<MovieList>() {}.type
        val saveFav: SharedPreferences = applicationContext.getSharedPreferences(getString(R.string.session), Context.MODE_PRIVATE)
        val editFav: SharedPreferences.Editor = saveFav.edit()
        val gson = Gson()
        val mu: MovieList? = gson.fromJson(json, type)
        var movieList = MovieList(arrayListOf())//Log.e("OK", mu.toString())
        mu?.let { it -> movieList = it }
        mutableList = movieList.results as MutableList<Movie>
        val ex = intent.getParcelableExtra<Movie>(MOVIE_KEY)
        checkButton(ex, mutableList)
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

        btnFav.setOnClickListener {
            favManager(ex, editFav, gson, mutableList)
        }
        setAdapter()
    }

    @SuppressLint("SetTextI18n")
    private fun checkButton(ex: Movie, mutableList: MutableList<Movie>) {
        when (mutableList.checkNonDuplicate(ex)) {
            0 -> {
                btnFav.text = getString(R.string.fuck_message)
            }
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun favManager(ex: Movie, editFav: SharedPreferences.Editor, gson: Gson, mutableList: MutableList<Movie>) {
        when (mutableList.checkNonDuplicate(ex)) {
            1 -> {
                mutableList.add(ex)
                val ml = MovieList(mutableList)
                val convert: String = gson.toJson(ml)
                btnFav.text = getString(R.string.noctic_button)
                editFav.putString(getString(R.string.session_name_movie), convert)
                editFav.commit()//Log.e("check", mutableList.size.toString())
                Toast.makeText(applicationContext, getString(R.string.success), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(applicationContext,  getString(R.string.noctic_button), Toast.LENGTH_SHORT).show()
            }
        }
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