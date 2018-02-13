package com.example.fluke.training.main.fragment.discover

import com.example.fluke.training.base.BaseContractor
import com.example.fluke.training.model.MovieType
import com.example.fluke.training.model.TelevisionType

class DiscoverContractor {

    interface Presenter : BaseContractor.Presenter<View> {
        fun callMovieGenres()
        fun callTelevisionGenres()
    }

    interface View : BaseContractor.View {
        fun genresMovieGenres(arr: List<MovieType>)
        fun genresTelevisionGenres(arr: List<TelevisionType>)
    }
}