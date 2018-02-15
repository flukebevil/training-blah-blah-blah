package com.example.fluke.training.ui.detail.television

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.fluke.training.*
import com.example.fluke.training.base.BaseActivity
import com.example.fluke.training.base.BaseUrl
import com.example.fluke.training.detail.DetailContract
import com.example.fluke.training.detail.television.DetailTelevisionPresenter
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.model.*
import com.example.fluke.training.ui.detail.movie.DetailMovieActivity
import com.example.fluke.training.ui.detail.movie.adapter.DetailActorAdapter
import com.example.fluke.training.ui.detail.movie.adapter.DetailCrewAdapter
import com.example.fluke.training.ui.detail.television.adapter.TelevisionSeasonAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_detail.*

@SuppressLint("Registered")
class DetailTelevisionActivity : BaseActivity<DetailContract.View, DetailTelevisionPresenter>(), DetailContract.View {
    private val adapterActor: DetailActorAdapter by lazy { DetailActorAdapter(arrayListOf()) }
    private val adapterCrew: DetailCrewAdapter by lazy { DetailCrewAdapter(arrayListOf()) }
    private val seasonAdapter : TelevisionSeasonAdapter by lazy { TelevisionSeasonAdapter(arrayListOf()) }

    override fun setSeasonCount(season: List<String>, tvId: String) {
            seasonAdapter.setItem(season , tvId)
        }

        override fun getVideo(moviePath: MovieVideoPath) {
            Intent(Intent.ACTION_VIEW, Uri.parse(BaseUrl.baseYouUrl + moviePath.key))
    }

    override fun getViewCrew(arr: List<Crew>?) {
        arr?.let { adapterCrew.setItem(it) }
    }

    override fun getViewActor(arr: List<CreditActor>?) {
        arr?.let { adapterActor.setItem(it) }
    }

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun layoutContentView(): Int = R.layout.activity_detail

    override fun setupView() {
        val saveFav: SharedPreferences = applicationContext.getSharedPreferences(getString(R.string.session), Context.MODE_PRIVATE)
        val editFav: SharedPreferences.Editor = saveFav.edit()
        val gson = Gson()
        val mutableList: MutableList<Television>
        val json: String? = saveFav.getString(getString(R.string.sesstion_name), "")
        val type = object : TypeToken<TelevisionList>() {}.type
        val mu: TelevisionList? = gson.fromJson(json, type)
        var televisionList = TelevisionList(arrayListOf())//Log.e("OK", mu.toString())
        mu?.let { it -> televisionList = it }
        mutableList = televisionList.results as MutableList<Television>
        val ex = intent.getParcelableExtra<Television>(MOVIE_KEY)
        checkButton(ex, mutableList)
        seasonFrame.visibility = View.VISIBLE
        rateManager(gson , ex)
        seasonList.apply {
            visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(context)
            adapter = seasonAdapter
        }

        tvName.text = ex.name
        tvDesc.text = ex.overview
        ivBackdrop.apply {
            load(BaseUrl.baseUrlImageMovie + ex.backdrop_path)
            setOnClickListener { presenter.getVideoPath(ex.id) }
        }
        presenter.apply {
            getDataActor(ex.id)
            getDataCrew(ex.id)
            getSeason(ex.id)
        }

        btnFav.setOnClickListener {
            favManager(ex, editFav, gson , mutableList)
        }

        setWishList()

        setAdapter()
        setAdapter()
    }

    @SuppressLint("CommitPrefEdits", "ApplySharedPref")
    private fun rateManager(gson: Gson, ex: Television) {
        val rateListMutation: MutableList<Rating>
        val rateShow: SharedPreferences = applicationContext.getSharedPreferences("rating", Context.MODE_PRIVATE)
        val rateJson: String? = rateShow.getString(getString(R.string.sesstion_name), "")
        val rateType = object : TypeToken<RatingList>() {}.type
        val editFav: SharedPreferences.Editor = rateShow.edit()
        val mu: RatingList? = gson.fromJson(rateJson, rateType)
        var rateList = RatingList(arrayListOf())//Log.e("OK", mu.toString())
        mu?.let { it -> rateList = it }
        rateListMutation = rateList.rate as MutableList<Rating>

        btnRate.setOnClickListener {
            when (rateListMutation.checkTelevisionRated(ex)){
                -1 -> {
                    val rated : Rating? = ex.id?.toInt()?.let { it1 -> Rating(it1, rating.rating) }
                    rated?.let { it1 -> rateListMutation.add(it1) }
                    ex.vote_average = rating.rating.toString()
                    val ml = RatingList(rateListMutation)
                    val convert: String = gson.toJson(ml)
                    editFav.putString(getString(R.string.sesstion_name), convert)
                    editFav.commit()
                    Log.e("pokwef" , ex.vote_average)
                }
                else -> {
                    rateListMutation[rateListMutation.checkTelevisionRated(ex)].rating = rating.rating
                    val ml = RatingList(rateListMutation)
                    val convert: String = gson.toJson(ml)
                    editFav.putString(getString(R.string.sesstion_name), convert)
                    editFav.commit()
                    Log.e("pokwef" , rateListMutation[rateListMutation.checkTelevisionRated(ex)].rating.toString())
                }
            }
        }

        getRating(ex , rateListMutation)
    }

    private fun getRating(ex : Television , mutableList: MutableList<Rating>){
        when (mutableList.checkTelevisionRated(ex)){
            -1 ->  rating.rating = ex.vote_average?.toFloat().div(2)
            else -> {
                rating.rating = mutableList[mutableList.checkTelevisionRated(ex)].rating}
        }
    }

    private fun setWishList() {
        val saveFav: SharedPreferences = applicationContext.getSharedPreferences("later", Context.MODE_PRIVATE)
        val editFav: SharedPreferences.Editor = saveFav.edit()
        val gson = Gson()
        val mutableList: MutableList<Television>
        val json: String? = saveFav.getString(getString(R.string.sesstion_name), "")
        val type = object : TypeToken<TelevisionList>() {}.type
        val mu: TelevisionList? = gson.fromJson(json, type)
        var televisionList = TelevisionList(arrayListOf())//Log.e("OK", mu.toString())
        mu?.let { it -> televisionList = it }
        mutableList = televisionList.results as MutableList<Television>
        val ex = intent.getParcelableExtra<Television>(MOVIE_KEY)
        checkWishButton(ex, mutableList)

        btnLater.setOnClickListener { laterManager(ex, editFav, gson, mutableList) }
    }

    @SuppressLint("ApplySharedPref", "SetTextI18n")
    private fun laterManager(ex: Television, editFav: SharedPreferences.Editor, gson: Gson, mutableList: MutableList<Television>) {
        when (mutableList.checkNonDuplicate(ex)) {
            1 -> {
                mutableList.add(ex)
                val ml = TelevisionList(mutableList)
                val convert: String = gson.toJson(ml)
                btnLater.text = getString(R.string.success_fuck_text)
                editFav.putString(getString(R.string.sesstion_name), convert)
                editFav.commit()//Log.e("check", mutableList.size.toString())
                Toast.makeText(applicationContext, getString(R.string.success), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(applicationContext, getString(R.string.noctic_button), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkWishButton(ex: Television, mutableList: MutableList<Television>) {
        when (mutableList.checkNonDuplicate(ex)) {
            0 -> {
                btnLater.text = getString(R.string.fuck_message)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkButton(ex: Television, mutableList: MutableList<Television>) {
        when (mutableList.checkNonDuplicate(ex)) {
            0 -> {
                btnFav.text =  getString(R.string.noctic_button)
            }
        }
    }

    @SuppressLint("SetTextI18n", "ShowToast")
    private fun favManager(ex: Television, editFav: SharedPreferences.Editor, gson: Gson, mutableList: MutableList<Television>) {
        when (mutableList.checkNonDuplicate(ex)) {
            1 -> {
                mutableList.add(ex)
                val ml = TelevisionList(mutableList)
                val convert: String = gson.toJson(ml)
                editFav.putString(getString(R.string.sesstion_name), convert)
                btnFav.text =  getString(R.string.noctic_button)
                editFav.commit()
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
        const val MOVIE_KEY = "TELEVISIONABC"
    }
}