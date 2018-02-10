package com.example.fluke.training.result

import com.example.fluke.training.base.BasePresenter
import com.example.fluke.training.base.BaseSubScribe
import com.example.fluke.training.main.fragment.discover.DiscoverContractor
import com.example.fluke.training.model.GetData
import com.example.fluke.training.model.Movie
import com.example.fluke.training.model.MovieList
import com.example.fluke.training.model.TelevisionList
import javax.inject.Inject

class ResultPresenter @Inject constructor(private val getData: GetData) :
        BasePresenter<ResultContractor.View>(),
        BaseSubScribe.ResponseWtf<MovieList>,
        ResultContractor.Presenter {
    override fun getDataFromKeyWord(key:String) = getData.requestMovieDataSearch(key,this)

    override fun success(t: MovieList) {
       t.results?.let { getView()?.getViewDataKeyWord(it) }
    }
}