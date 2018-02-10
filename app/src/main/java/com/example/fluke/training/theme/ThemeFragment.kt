package com.example.fluke.training.theme

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseFragment
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.main.fragment.discover.DiscoverContractor
import com.example.fluke.training.main.fragment.movie.Contractor
import com.example.fluke.training.main.fragment.movie.presenter.MoviePresenter
import com.example.fluke.training.model.MovieType
import kotlinx.android.synthetic.main.fragment_home.*

class ThemeFragment : BaseFragment<Contractor.View, MoviePresenter>(), ThemeContractor.View {

    //private val adapterPoster: MoviePosterAdapter by lazy { MoviePosterAdapter(arrayListOf()) }
    override fun setAdapter() {
        popList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            // adapter = adapterPoster
        }
    }

    override fun initFunction() {
        // presenter.startCallPopData()
    }

    override fun layoutInflate(): Int = R.layout.fragment_home

    override fun doInjection(appComponent: ApplicationComponent) {
        // appComponent.inject(this)
    }
}
