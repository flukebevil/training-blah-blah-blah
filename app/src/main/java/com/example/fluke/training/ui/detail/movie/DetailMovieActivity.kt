package com.example.fluke.training.ui.detail.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.util.Log
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseActivity
import com.example.fluke.training.detail.movie.DetailContract
import com.example.fluke.training.detail.movie.DetailPresenter
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.load
import com.example.fluke.training.model.CreditActor
import com.example.fluke.training.model.Crew
import com.example.fluke.training.model.Movie
import com.example.fluke.training.model.MovieVideoPath
import com.example.fluke.training.ui.detail.movie.adapter.DetailActorAdapter
import com.example.fluke.training.ui.detail.movie.adapter.DetailCrewAdapter
import com.example.fluke.training.base.BaseUrl
import kotlinx.android.synthetic.main.activity_detail.*

@SuppressLint("Registered")
class DetailMovieActivity : BaseActivity<DetailContract.View, DetailPresenter>(), DetailContract.View {

    override fun getVideo(moviePath: MovieVideoPath) {
        applicationContext.startActivity(Intent(Intent.ACTION_VIEW , Uri.parse(BaseUrl.baseYouUrl+moviePath.key)))
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
        presenter.getDataActor(ex.id)
        presenter.getDataCrew(ex.id)
        setAdapter()
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
