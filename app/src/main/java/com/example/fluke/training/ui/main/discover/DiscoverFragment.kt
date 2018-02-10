package com.example.fluke.training.ui.main.discover

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseFragment
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.main.fragment.discover.DiscoverContractor
import com.example.fluke.training.main.fragment.discover.DiscoverPresenter
import com.example.fluke.training.main.fragment.discover.adapter.MovieGenresAdapter
import com.example.fluke.training.main.fragment.discover.adapter.TelevisionGenresAdapter
import com.example.fluke.training.model.MovieType
import com.example.fluke.training.model.TelevisionType
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment : BaseFragment<DiscoverContractor.View, DiscoverPresenter>(), DiscoverContractor.View {
    private val movieGenresGenresAdapter: MovieGenresAdapter by lazy { MovieGenresAdapter(listOf()) }
    private val televisionGenresAdapter: TelevisionGenresAdapter by lazy { TelevisionGenresAdapter(listOf()) }

    override fun genresTelevisionGenres(arr: List<TelevisionType>) {
        arr.let { it -> televisionGenresAdapter.setItem(it) }
    }

    override fun genresMovieGenres(arr: List<MovieType>) {
        movieGenresGenresAdapter.setItem(arr)
    }

    override fun setAdapter() {
        listMovieGenres.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = movieGenresGenresAdapter
        }
        listTelevisionGenres.apply {
            layoutManager = LinearLayoutManager(context , OrientationHelper.HORIZONTAL , false)
            adapter = televisionGenresAdapter
        }
    }

    override fun initFunction() {
        presenter.callMovieGenres()
        presenter.callTelevisionGenres()
    }

    override fun layoutInflate(): Int = R.layout.fragment_discover

    override fun doInjection(appComponent: ApplicationComponent) {
        appComponent.inject(this)
    }
}
