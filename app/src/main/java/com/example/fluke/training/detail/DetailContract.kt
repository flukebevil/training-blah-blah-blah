package com.example.fluke.training.detail

import com.example.fluke.training.base.BaseContractor
import com.example.fluke.training.model.CreditActor
import com.example.fluke.training.model.Crew
import com.example.fluke.training.model.MovieVideoPath

interface DetailContract {
    interface Presenter : BaseContractor.Presenter<View> {
        fun getDataActor(movieId: String?)
        fun getDataCrew(movieId: String?)
        fun getVideoPath(movieId: String?)
        fun getSeason(tvId: String)
    }

    interface View : BaseContractor.View {
        fun getViewActor(arr: List<CreditActor>?)
        fun getViewCrew(arr: List<Crew>?)
        fun getVideo(moviePath: MovieVideoPath)
        fun setSeasonCount(season: List<String>, tvId: String)
    }
}