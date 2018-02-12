package com.example.fluke.training.detail.television

import com.example.fluke.training.base.BasePresenter
import com.example.fluke.training.base.BaseSubScribe
import com.example.fluke.training.detail.movie.DetailContract
import com.example.fluke.training.model.CreditList
import com.example.fluke.training.model.GetData
import com.example.fluke.training.model.MovieVideoPathList
import javax.inject.Inject

class DetailTelevisionPresenter @Inject constructor(private val getData: GetData) :
        BasePresenter<DetailContract.View>(),
        BaseSubScribe.ResponseWtf<CreditList>,
        DetailContract.Presenter,
        GetData.DetailListener {
    override fun getVideoPath(movieId: String?) = getData.requestTvVideoPath(movieId.toString() , this)

    override fun onCallVideoPathSuccess(t: MovieVideoPathList) {
        getView()?.getVideo(t.results[0])
    }

    override fun onCrewCallback(t: CreditList) {
        getView()?.getViewCrew(t.crew_data)
    }

    override fun getDataActor(movieId: String?) = getData.requestTelevisionCredit(movieId.toString(), this)

    override fun getDataCrew(movieId: String?) = getData.requestTelevisionCrew(movieId.toString(), this)

    override fun success(t: CreditList) {
        getView()?.getViewActor(t.cast_data)
    }
}