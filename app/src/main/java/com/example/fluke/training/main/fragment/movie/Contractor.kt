package com.example.fluke.training.main.fragment.movie

import com.example.fluke.training.base.BaseContractor
import com.example.fluke.training.model.Movie

class Contractor {
    interface Presenter : BaseContractor.Presenter<View> {
        fun startCallPopData()
        fun startCallTopData()
        fun startCallUpcomingData()
    }

    interface View : BaseContractor.View {
        fun holdPopData(arr: List<Movie>?)
        fun holdTopData(arr: List<Movie>?)
        fun holdUpComingData(arr: List<Movie>?)
    }
}