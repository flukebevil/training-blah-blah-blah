package com.example.fluke.training.ui.main.discover

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.KeyEvent
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseFragment
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.main.fragment.discover.DiscoverContractor
import com.example.fluke.training.main.fragment.discover.DiscoverPresenter
import com.example.fluke.training.model.MovieType
import com.example.fluke.training.model.TelevisionType
import com.example.fluke.training.ui.main.discover.adapter.MovieGenresAdapter
import com.example.fluke.training.ui.main.discover.adapter.TelevisionGenresAdapter
import com.example.fluke.training.ui.result.ResultActivity
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
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = televisionGenresAdapter
        }
        edtSearch.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                context?.startActivity(Intent(context, ResultActivity::class.java)
                        .putExtra(KEY_SEARCH, edtSearch.text.toString()))
                true
            } else false
        }
    }

    override fun initFunction() {
        presenter.apply {
            callMovieGenres()
            callTelevisionGenres()
        }
    }

    override fun layoutInflate(): Int = R.layout.fragment_discover

    override fun doInjection(appComponent: ApplicationComponent) {
        appComponent.inject(this)
    }

    companion object {
        const val KEY_MOVIE = "OBJGENRES"
        const val KEY_TELEVISION = "OBJTELE"
        const val KEY_SEARCH = "OBJSEARCH"
    }
}