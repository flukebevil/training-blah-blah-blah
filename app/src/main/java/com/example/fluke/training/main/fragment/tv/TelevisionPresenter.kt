package com.example.fluke.training.main.fragment.tv

import com.example.fluke.training.base.BasePresenter
import com.example.fluke.training.base.BaseSubScribe
import com.example.fluke.training.model.GetData
import com.example.fluke.training.model.TelevisionList
import javax.inject.Inject

class TelevisionPresenter @Inject constructor(private val getData: GetData) :
        BasePresenter<TvContractor.View>(),
        BaseSubScribe.ResponseWtf<TelevisionList>,
        GetData.TelevisionTopRateListener,
        TvContractor.Presenter {

    override fun startCallUpComing() {
        getData.requestTelevisionUpComing(this)
    }

    override fun upComingSuccess(t: TelevisionList) {
        t.results?.let { it -> getView()?.viewUpcoming(arr = it) }
    }

    override fun startCallTopRateData() {
        getData.requestTelevisionTopRate(this)
    }

    override fun success(t: TelevisionList) {
        t.results?.let { getView()?.viewPopData(arr = it) }
    }

    override fun onTopRateSuccess(t: TelevisionList) {
        t.results?.let { getView()?.viewTopData(arr = it) }
    }

    override fun startCallPopData() {
        getData.requestTelevisionPopData(this)
    }
}