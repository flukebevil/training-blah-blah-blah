package com.example.fluke.training.detail.movie

import com.example.fluke.training.base.BaseContractor
import com.example.fluke.training.model.*

interface DetailContract {
    interface Presenter : BaseContractor.Presenter<View> {
        fun getDataActor(movieId: String?)
        fun getDataCrew(movieId: String?)
        fun getVideoPath(movieId: String?)
    }

    interface View : BaseContractor.View {
        fun getViewActor(arr: List<CreditActor>?)
        fun getViewCrew(arr: List<Crew>?)
        fun getVideo(moviePath : MovieVideoPath)
    }
}