package com.example.fluke.training.ui.detail.television

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
import com.example.fluke.training.detail.television.DetailTelevisionPresenter
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.load
import com.example.fluke.training.model.*
import com.example.fluke.training.ui.detail.movie.adapter.DetailActorAdapter
import com.example.fluke.training.ui.detail.movie.adapter.DetailCrewAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_detail.*

@SuppressLint("Registered")
class DetailTelevisionActivity : BaseActivity<DetailContract.View, DetailTelevisionPresenter>(), DetailContract.View {
    override fun getVideo(moviePath: MovieVideoPath) {
        Intent(Intent.ACTION_VIEW, Uri.parse(BaseUrl.baseYouUrl + moviePath.key))
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
        val saveFav: SharedPreferences = applicationContext.getSharedPreferences(getString(R.string.session), Context.MODE_PRIVATE)
        val editFav: SharedPreferences.Editor = saveFav.edit()
        val gson = Gson()
        val mutableList: MutableList<Television>
        val show: SharedPreferences = applicationContext.getSharedPreferences(getString(R.string.session), Context.MODE_PRIVATE)
        val json: String? = show.getString(getString(R.string.sesstion_name), "")
        val type = object : TypeToken<TelevisionList>() {}.type
        val mu: TelevisionList? = gson.fromJson(json, type)
        var televisionList = TelevisionList(arrayListOf())//Log.e("OK", mu.toString())
        mu?.let { it -> televisionList = it }
        mutableList = televisionList.results as MutableList<Television>
        val ex = intent.getParcelableExtra<Television>(MOVIE_KEY)

        checkButton(ex, mutableList)

        tvName.text = ex.name
        tvDesc.text = ex.overview
        ivBackdrop.apply {
            load(BaseUrl.baseUrlImageMovie + ex.backdrop_path)
            setOnClickListener { presenter.getVideoPath(ex.id) }
        }
        presenter.apply {
            getDataActor(ex.id)
            getDataCrew(ex.id)
        }

        btnFav.setOnClickListener {
            favManager(ex, editFav, gson , mutableList)
        }
        setAdapter()
        setAdapter()
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