package com.example.fluke.training.main.fragment.discover

import com.example.fluke.training.base.BasePresenter
import com.example.fluke.training.base.BaseSubScribe
import com.example.fluke.training.model.GetData
import com.example.fluke.training.model.MovieTypeList
import com.example.fluke.training.model.TelevisionType
import com.example.fluke.training.model.TelevisionTypeList
import javax.inject.Inject

class DiscoverPresenter @Inject constructor(private val getData: GetData) :
        BasePresenter<DiscoverContractor.View>(),
        BaseSubScribe.ResponseWtf<TelevisionType>,
        DiscoverContractor.Presenter,
        GetData.DiscoverListener {
    override fun onCallGenresTelevisionSuccess(t: TelevisionTypeList) {
        getView()?.genresTelevisionGenres(t.type)
    }

    override fun callTelevisionGenres() = getData.requestTelevisionGenres(this)

    override fun onCallGenresMovieSuccess(t: MovieTypeList) {
        getView()?.genresMovieGenres(t.type)
    }

    override fun callMovieGenres() = getData.requestMovieGenres(this)

    override fun success(t: TelevisionType) {}
}