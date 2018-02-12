package com.example.fluke.training.result

import com.example.fluke.training.base.BaseContractor
import com.example.fluke.training.model.Movie
import com.example.fluke.training.model.Television

class ResultContractor {

    interface Presenter : BaseContractor.Presenter<View> {
        fun getDataFromKeyWord(key: String)
        fun getDataFromMovieGenres(genresId: String)
        fun getDataFromTvGenres(genresId: String)
    }

    interface View : BaseContractor.View {
        fun getViewDataKeyWord(arr: List<Movie>)
        fun getViewMovieGenres(arr: List<Movie>)
        fun getViewTvGenres(arr: List<Television>)
    }
}