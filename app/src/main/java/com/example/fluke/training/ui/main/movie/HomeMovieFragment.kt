package com.example.fluke.training.ui.main.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseFragment
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.main.fragment.movie.Contractor
import com.example.fluke.training.main.fragment.movie.presenter.MoviePresenter
import com.example.fluke.training.model.Movie
import com.example.fluke.training.ui.main.movie.adapter.MoviePosterAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeMovieFragment : BaseFragment<Contractor.View, MoviePresenter>(), Contractor.View {
    private val adapterPopPoster: MoviePosterAdapter by lazy { MoviePosterAdapter(arrayListOf()) }
    private val adapterTopPoster: MoviePosterAdapter by lazy { MoviePosterAdapter(arrayListOf()) }
    private val adapterUpcomingPoster: MoviePosterAdapter by lazy { MoviePosterAdapter(arrayListOf()) }

    override fun holdUpComingData(arr: List<Movie>?) {
        arr?.let {it -> adapterUpcomingPoster.setItem(it) }
    }

    override fun holdTopData(arr: List<Movie>?) {
        arr?.let { it ->
            adapterTopPoster.setItem(it)
        }
    }

    override fun setAdapter() {
        popList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = adapterPopPoster
        }
        topList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = adapterTopPoster
        }
        upComingList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = adapterUpcomingPoster
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initFunction() {
        presenter.startCallPopData()
        presenter.startCallTopData()
        presenter.startCallUpcomingData()
    }

    override fun layoutInflate(): Int = R.layout.fragment_home

    override fun doInjection(appComponent: ApplicationComponent) {
        appComponent.inject(this)
    }

    override fun holdPopData(arr: List<Movie>?) {
        arr?.let { it ->
            adapterPopPoster.setItem(it)
        }
    }
}
