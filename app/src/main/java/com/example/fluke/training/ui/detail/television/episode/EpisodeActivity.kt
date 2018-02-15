package com.example.fluke.training.ui.detail.television.episode

import android.support.v7.widget.LinearLayoutManager
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseActivity
import com.example.fluke.training.detail.television.episode.EpisodeContractor
import com.example.fluke.training.detail.television.episode.EpisodePresenter
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.model.Episode
import com.example.fluke.training.ui.detail.television.episode.adapter.EpisodeAdapter
import kotlinx.android.synthetic.main.activity_episode.*

class EpisodeActivity : BaseActivity<EpisodeContractor.View, EpisodePresenter>(), EpisodeContractor.View {
    private val adapterEpisode: EpisodeAdapter by lazy { EpisodeAdapter(arrayListOf()) }

    override fun getViewEpisode(arr: List<Episode>?) {
        arr?.let { adapterEpisode.setItem(it) }
    }

    override fun layoutContentView(): Int = R.layout.activity_episode

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun setupView() {
        presenter.getEpisodeData(intent.getStringExtra(TV_ID_KEY), intent.getStringExtra(EPISODE_KEY))

        episodeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterEpisode
        }
    }

    companion object {
        val EPISODE_KEY = "EPISODE"
        val TV_ID_KEY = "TELEVISION"
    }
}