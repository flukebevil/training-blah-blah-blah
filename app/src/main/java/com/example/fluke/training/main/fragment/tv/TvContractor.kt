package com.example.fluke.training.main.fragment.tv

import com.example.fluke.training.base.BaseContractor
import com.example.fluke.training.model.Television

class TvContractor {

    interface Presenter : BaseContractor.Presenter<View> {
        fun startCallPopData()
        fun startCallTopRateData()
        fun startCallUpComing()
    }

    interface View : BaseContractor.View {
        fun viewPopData(arr: List<Television>?)
        fun viewTopData(arr: List<Television>?)
        fun viewUpcoming(arr: List<Television>?)
    }
}