package com.example.fluke.training.detail.movie

import com.example.fluke.training.base.BasePresenter
import com.example.fluke.training.base.BaseSubScribe
import com.example.fluke.training.detail.DetailContract
import com.example.fluke.training.model.CreditList
import com.example.fluke.training.model.GetData
import com.example.fluke.training.model.MovieVideoPathList
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val getData: GetData) :
        BasePresenter<DetailContract.View>(),
        BaseSubScribe.ResponseWtf<CreditList>,
        DetailContract.Presenter,
        GetData.DetailListener {

    override fun getSeason(movieId: String) {}

    override fun onTvDetailSuccess(t: String, tvId: String) {}

    override fun onCallVideoPathSuccess(t: MovieVideoPathList) {
        getView()?.getVideo(t.results[0])
    }

    override fun getVideoPath(movieId: String?) = getData.requestMovieVideoPath(movieId.toString(), this)

    override fun onCrewCallback(t: CreditList) {
        getView()?.getViewCrew(t.crew_data)
    }

    override fun getDataActor(movieId: String?) = getData.requestMovieCredit(movieId.toString(), this)

    override fun getDataCrew(movieId: String?) = getData.requestMovieCrew(movieId.toString(), this)

    override fun success(t: CreditList) {
        getView()?.getViewActor(t.cast_data)
    }
}