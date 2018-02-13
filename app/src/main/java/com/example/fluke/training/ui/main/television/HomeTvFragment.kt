package com.example.fluke.training.ui.main.television

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseFragment
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.main.fragment.tv.TelevisionPresenter
import com.example.fluke.training.main.fragment.tv.TvContractor
import com.example.fluke.training.model.Television
import com.example.fluke.training.ui.main.television.adapter.TelevisionPosterAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeTvFragment : BaseFragment<TvContractor.View, TelevisionPresenter>(), TvContractor.View {
    private val adapterPoster: TelevisionPosterAdapter by lazy { TelevisionPosterAdapter(arrayListOf()) }
    private val adapterTopPoster: TelevisionPosterAdapter by lazy { TelevisionPosterAdapter(arrayListOf()) }
    private val adapterUpcoming: TelevisionPosterAdapter by lazy { TelevisionPosterAdapter(arrayListOf()) }

    override fun viewUpcoming(arr: List<Television>?) {
        arr?.let { it -> adapterUpcoming.setItem(it) }
    }

    override fun viewTopData(arr: List<Television>?) {
        arr?.let { it -> adapterTopPoster.setItem(it) }
    }

    override fun viewPopData(arr: List<Television>?) {
        arr?.let { it -> adapterPoster.setItem(it) }
    }

    override fun setAdapter() {
        popList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = adapterPoster
        }
        topList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = adapterTopPoster
        }
        upComingList.apply {
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = adapterUpcoming
        }
    }

    override fun initFunction() {
        presenter.apply {
            startCallPopData()
            startCallTopRateData()
            startCallUpComing()
        }
    }

    override fun layoutInflate(): Int = R.layout.fragment_home

    override fun doInjection(appComponent: ApplicationComponent) {
        appComponent.inject(this)
    }
}