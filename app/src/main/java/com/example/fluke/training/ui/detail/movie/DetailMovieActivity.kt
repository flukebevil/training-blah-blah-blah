package com.example.fluke.training.ui.detail.movie

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import android.widget.Toast
import com.example.fluke.training.*
import com.example.fluke.training.base.BaseActivity
import com.example.fluke.training.base.BaseUrl
import com.example.fluke.training.detail.DetailContract
import com.example.fluke.training.detail.movie.DetailPresenter
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.model.*
import com.example.fluke.training.model.Rating
import com.example.fluke.training.ui.detail.movie.adapter.DetailActorAdapter
import com.example.fluke.training.ui.detail.movie.adapter.DetailCrewAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_detail.*

@SuppressLint("Registered")
class DetailMovieActivity : BaseActivity<DetailContract.View, DetailPresenter>(), DetailContract.View {
    private val adapterActor: DetailActorAdapter by lazy { DetailActorAdapter(arrayListOf()) }
    private val adapterCrew: DetailCrewAdapter by lazy { DetailCrewAdapter(arrayListOf()) }

    override fun setSeasonCount(season: List<String>, tvId: String) {}

    override fun getVideo(moviePath: MovieVideoPath) {
        applicationContext.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BaseUrl.baseYouUrl + moviePath.key)))
    }

    override fun getViewCrew(arr: List<Crew>?) {
        arr?.let { adapterCrew.setItem(it) }
    }

    override fun getViewActor(arr: List<CreditActor>?) {
        arr?.let { adapterActor.setItem(it) }
    }

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun layoutContentView(): Int = R.layout.activity_detail

    @SuppressLint("ApplySharedPref")
    override fun setupView() {
        val mutableList: MutableList<Movie>
        val show: SharedPreferences = applicationContext.getSharedPreferences(getString(R.string.session), Context.MODE_PRIVATE)
        val json: String? = show.getString(getString(R.string.session_name_movie), "")
        val type = object : TypeToken<MovieList>() {}.type
        val editFav: SharedPreferences.Editor = show.edit()
        val gson = Gson()
        val mu: MovieList? = gson.fromJson(json, type)
        var movieList = MovieList(arrayListOf())//Log.e("OK", mu.toString())
        mu?.let { it -> movieList = it }
        mutableList = movieList.results as MutableList<Movie>
        val ex = intent.getParcelableExtra<Movie>(MOVIE_KEY)
        checkButton(ex, mutableList)
        tvName.text = ex.title

       // getRating(ex , mutableList)
        rateManager(gson , ex)
       // btnRate.setOnClickListener {
//            when (mutableList.checkRating(ex)){
//                -1 -> Toast.makeText(applicationContext
//                        , "You need to hit Fav Button Before set this fucking rating",Toast.LENGTH_SHORT).show()
//
//                else -> {
//                    mutableList[mutableList.checkRating(ex)].vote_average = rating.rating.toString()
//                    val ml = MovieList(mutableList)
//                    val convert: String = gson.toJson(ml)
//                    editFav.putString(getString(R.string.session_name_movie), convert)
//                    editFav.commit()
//                    Log.e("pokwef" , ex.vote_average)
//                }
//            }


      //  }

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

        setWishList()
        setAdapter()
    }

    @SuppressLint("CommitPrefEdits", "ApplySharedPref")
    private fun rateManager(gson: Gson, ex: Movie) {
        val rateListMutation: MutableList<Rating>
        val rateShow: SharedPreferences = applicationContext.getSharedPreferences("rating", Context.MODE_PRIVATE)
        val rateJson: String? = rateShow.getString(getString(R.string.session_name_movie), "")
        val rateType = object : TypeToken<RatingList>() {}.type
        val editFav: SharedPreferences.Editor = rateShow.edit()
        val mu: RatingList? = gson.fromJson(rateJson, rateType)
        var rateList = RatingList(arrayListOf())//Log.e("OK", mu.toString())
        mu?.let { it -> rateList = it }
        rateListMutation = rateList.rate as MutableList<Rating>

        btnRate.setOnClickListener {
            when (rateListMutation.checkMovieRated(ex)){
                -1 -> {
                    val rated : Rating? = ex.id?.toInt()?.let { it1 -> Rating(it1, rating.rating) }
                    rated?.let { it1 -> rateListMutation.add(it1) }
                    ex.vote_average = rating.rating.toString()
                    val ml = RatingList(rateListMutation)
                    val convert: String = gson.toJson(ml)
                    editFav.putString(getString(R.string.session_name_movie), convert)
                    editFav.commit()
                    Log.e("pokwef" , ex.vote_average)
                }
                else -> {
                    rateListMutation[rateListMutation.checkMovieRated(ex)].rating = rating.rating
                    val ml = RatingList(rateListMutation)
                    val convert: String = gson.toJson(ml)
                    editFav.putString(getString(R.string.session_name_movie), convert)
                    editFav.commit()
                    Log.e("pokwef" , rateListMutation[rateListMutation.checkMovieRated(ex)].rating.toString())
                }
            }
        }

        getRating(ex , rateListMutation)
    }

    private fun getRating(ex : Movie , mutableList: MutableList<Rating>){
         when (mutableList.checkMovieRated(ex)){
            -1 ->  rating.rating = ex.vote_average?.toFloat()?.div(2) ?: 0.toFloat()
            else -> {
                rating.rating = mutableList[mutableList.checkMovieRated(ex)].rating}
        }
    }

    private fun setWishList() {
        val mutableList: MutableList<Movie>
        val show: SharedPreferences = applicationContext.getSharedPreferences("later", Context.MODE_PRIVATE)
        val json: String? = show.getString(getString(R.string.session_name_movie), "")
        val type = object : TypeToken<MovieList>() {}.type
        val editFav: SharedPreferences.Editor = show.edit()
        val gson = Gson()
        val mu: MovieList? = gson.fromJson(json, type)
        var movieList = MovieList(arrayListOf())//Log.e("OK", mu.toString())
        mu?.let { it -> movieList = it }
        mutableList = movieList.results as MutableList<Movie>
        val ex = intent.getParcelableExtra<Movie>(MOVIE_KEY)
        checkWishButton(ex, mutableList)

        btnLater.setOnClickListener { laterManager(ex, editFav, gson, mutableList) }

    }

    private fun checkWishButton(ex: Movie, mutableList: MutableList<Movie>) {
        when (mutableList.checkNonDuplicate(ex)) {
            0 -> {
                btnLater.text = getString(R.string.fuck_message)
            }
        }
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
                Toast.makeText(applicationContext, getString(R.string.noctic_button), Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("ApplySharedPref", "SetTextI18n")
    private fun laterManager(ex: Movie, editFav: SharedPreferences.Editor, gson: Gson, mutableList: MutableList<Movie>) {
        when (mutableList.checkNonDuplicate(ex)) {
            1 -> {
                mutableList.add(ex)
                val ml = MovieList(mutableList)
                val convert: String = gson.toJson(ml)
                btnLater.text = getString(R.string.success_fuck_text)
                editFav.putString(getString(R.string.session_name_movie), convert)
                editFav.commit()//Log.e("check", mutableList.size.toString())
                Toast.makeText(applicationContext, getString(R.string.success), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(applicationContext, getString(R.string.noctic_button), Toast.LENGTH_SHORT).show()
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