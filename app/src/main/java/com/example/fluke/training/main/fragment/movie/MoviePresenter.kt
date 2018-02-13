package com.example.fluke.training.main.fragment.movie

import com.example.fluke.training.base.BasePresenter
import com.example.fluke.training.base.BaseSubScribe
import com.example.fluke.training.model.GetData
import com.example.fluke.training.model.MovieList
import javax.inject.Inject

class MoviePresenter @Inject constructor(private val getData: GetData) :
        BasePresenter<Contractor.View>(),
        BaseSubScribe.ResponseWtf<MovieList>,
        GetData.MovieTopRateListener,
        Contractor.Presenter {
    override fun startCallUpcomingData() = getData.requestMovieUpComing(this)

    override fun upComingSuccess(t: MovieList) {
        t.results?.let { getView()?.holdUpComingData(it) }
    }

    override fun onTopRateSuccess(t: MovieList) {
        t.results?.let { getView()?.holdTopData(arr = it) }
    }

    override fun startCallTopData() {
        getData.requestMovieTopRate(this)
    }

    override fun startCallPopData() {
        getData.requestMoviePopData(this)
    }

    override fun success(t: MovieList) {
        t.results?.let { getView()?.holdPopData(arr = it) }
    }
}