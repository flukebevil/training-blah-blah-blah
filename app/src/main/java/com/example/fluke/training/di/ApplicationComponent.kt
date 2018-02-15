package com.example.fluke.training.di

import com.example.fluke.training.di.network.ApiManager
import com.example.fluke.training.di.network.ApiModule
import com.example.fluke.training.ui.detail.movie.DetailMovieActivity
import com.example.fluke.training.ui.detail.television.DetailTelevisionActivity
import com.example.fluke.training.ui.detail.television.episode.EpisodeActivity
import com.example.fluke.training.ui.main.HomeActivity
import com.example.fluke.training.ui.main.discover.DiscoverFragment
import com.example.fluke.training.ui.main.movie.HomeMovieFragment
import com.example.fluke.training.ui.main.television.HomeTvFragment
import com.example.fluke.training.ui.result.ResultActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiManager::class), (ApiModule::class), (AndroidModule::class)])
interface ApplicationComponent {
    fun inject(homeMovieFragment: HomeMovieFragment)
    fun inject(homeActivity: HomeActivity)
    fun inject(homeTvFragment: HomeTvFragment)
    fun inject(discoverFragment: DiscoverFragment)
    fun inject(resultActivity: ResultActivity)
    fun inject(detailMovieActivity: DetailMovieActivity)
    fun inject(detailTelevisionActivity: DetailTelevisionActivity)
    fun inject(episodeActivity : EpisodeActivity)
}