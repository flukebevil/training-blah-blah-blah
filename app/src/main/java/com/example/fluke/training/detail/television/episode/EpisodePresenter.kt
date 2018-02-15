package com.example.fluke.training.detail.television.episode

import com.example.fluke.training.base.BasePresenter
import com.example.fluke.training.base.BaseSubScribe
import com.example.fluke.training.model.GetData
import com.example.fluke.training.model.TelevisionSeason
import javax.inject.Inject

class EpisodePresenter @Inject constructor(private val getData: GetData) : BasePresenter<EpisodeContractor.View>(),
        BaseSubScribe.ResponseWtf<TelevisionSeason>,
        EpisodeContractor.Presenter {
    override fun getEpisodeData(movieId: String? ,episode : String)
            = getData.requestEpisodeData(movieId.toString() , episode , this)

    override fun success(t: TelevisionSeason) {
        getView()?.getViewEpisode(t.episode)
    }
}