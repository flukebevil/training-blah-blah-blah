package com.example.fluke.training.detail.television.episode

import com.example.fluke.training.base.BaseContractor
import com.example.fluke.training.model.Episode

interface EpisodeContractor {
    interface Presenter : BaseContractor.Presenter<View> {
        fun getEpisodeData(movieId: String? ,episode : String)
    }

    interface View : BaseContractor.View {
        fun getViewEpisode(arr: List<Episode>?)
    }
}