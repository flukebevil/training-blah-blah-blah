package com.example.fluke.training.di

import com.example.fluke.training.di.network.ApiManager
import com.example.fluke.training.di.network.ApiModule
import com.example.fluke.training.ui.main.HomeActivity
import com.example.fluke.training.ui.main.discover.DiscoverFragment
import com.example.fluke.training.ui.main.movie.HomeMovieFragment
import com.example.fluke.training.ui.main.television.HomeTvFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiManager::class), (ApiModule::class), (AndroidModule::class)])
interface ApplicationComponent {
    fun inject(homeMovieFragment: HomeMovieFragment)
    fun inject(homeActivity: HomeActivity)
    fun inject(homeTvFragment: HomeTvFragment)
    fun inject(discoverFragment: DiscoverFragment)
}