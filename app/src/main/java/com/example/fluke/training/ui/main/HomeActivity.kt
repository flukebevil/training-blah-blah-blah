package com.example.fluke.training.ui.main

import android.view.Menu
import android.view.MenuItem
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseActivity
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.model.Movie
import com.example.fluke.training.result.ResultContractor
import com.example.fluke.training.result.ResultPresenter
import com.example.fluke.training.ui.main.adapter.Pager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity :  BaseActivity<ResultContractor.View, ResultPresenter>() {

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun layoutContentView(): Int = R.layout.activity_home
    private var pager: Pager? = null

    override fun setupView() {
        pager = Pager(supportFragmentManager)
        container.adapter = pager
        tabLayout?.setupWithViewPager(container)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        const val tab1 = "Movie"
        const val tab2 = "Television"
        const val tab3 = "Discover"
    }
}