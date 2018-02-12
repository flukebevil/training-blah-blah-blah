package com.example.fluke.training.result

import com.example.fluke.training.base.BasePresenter
import com.example.fluke.training.base.BaseSubScribe
import com.example.fluke.training.model.GetData
import com.example.fluke.training.model.MovieList
import com.example.fluke.training.model.TelevisionList
import javax.inject.Inject

class ResultPresenter @Inject constructor(private val getData: GetData) :
        BasePresenter<ResultContractor.View>(),
        BaseSubScribe.ResponseWtf<MovieList>,
        ResultContractor.Presenter,
        GetData.ResultListener {
    override fun onMovieGenresCallDetail(t: MovieList) {
        t.results?.let { it1 -> getView()?.getViewMovieGenres(it1) }
    }

    override fun onTelevisionGenresCallDetail(t: TelevisionList) {
       t.results?.let { it -> getView()?.getViewTvGenres(it) }
    }

    override fun getDataFromMovieGenres(genresId: String) = getData.requestMovieGenresResult(genresId, this)

    override fun getDataFromTvGenres(genresId: String) = getData.requestTelevisionGenresResult(genresId, this)

    override fun getDataFromKeyWord(key: String) = getData.requestMovieDataSearch(key, this)

    override fun success(t: MovieList) {
        t.results?.let { getView()?.getViewDataKeyWord(it) }
    }
}