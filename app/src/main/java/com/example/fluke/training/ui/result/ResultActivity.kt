package com.example.fluke.training.ui.result

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseActivity
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.model.Movie
import com.example.fluke.training.model.Television
import com.example.fluke.training.result.ResultContractor
import com.example.fluke.training.result.ResultPresenter
import com.example.fluke.training.ui.main.discover.DiscoverFragment
import com.example.fluke.training.ui.result.adapter.MovieAdapter
import com.example.fluke.training.ui.result.adapter.TelevisionAdapter
import kotlinx.android.synthetic.main.activity_result.*

@SuppressLint("Registered")
class ResultActivity : BaseActivity<ResultContractor.View, ResultPresenter>(), ResultContractor.View {
    override fun getViewMovieGenres(arr: List<Movie>) {
        arr.let { it -> movieAdapterSearch.setItem(it) }
    }

    override fun getViewTvGenres(arr: List<Television>) {
        arr.let { it -> televisionAdapter.setItem(it) }
    }

    private val movieAdapterSearch: MovieAdapter by lazy { MovieAdapter(arrayListOf()) }
    private val televisionAdapter: TelevisionAdapter by lazy { TelevisionAdapter(arrayListOf()) }

    override fun getViewDataKeyWord(arr: List<Movie>) {
        arr.let { it -> movieAdapterSearch.setItem(it) }
    }

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun layoutContentView(): Int = R.layout.activity_result

    override fun setupView() {
        val fuckMovie = intent.getStringExtra(DiscoverFragment.KEY_MOVIE)
        val fuckTv = intent.getStringExtra(DiscoverFragment.KEY_TELEVISION)
        val fuckSearch = intent.getStringExtra(DiscoverFragment.KEY_SEARCH)

        fuckMovie?.let {
            setAdapter()
            presenter.getDataFromMovieGenres(it)
        }

        fuckTv?.let {
            resultList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = televisionAdapter
            }
            presenter.getDataFromTvGenres(it)
        }

        fuckSearch?.let {
            setAdapter()
            presenter.getDataFromKeyWord(it)
        }
    }

    private fun setAdapter() {
        resultList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapterSearch
        }
    }
}