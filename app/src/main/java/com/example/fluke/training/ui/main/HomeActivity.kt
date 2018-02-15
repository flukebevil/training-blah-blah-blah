package com.example.fluke.training.ui.main

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.example.fluke.training.R
import com.example.fluke.training.base.BaseActivity
import com.example.fluke.training.di.ApplicationComponent
import com.example.fluke.training.result.ResultContractor
import com.example.fluke.training.result.ResultPresenter
import com.example.fluke.training.ui.main.adapter.Pager
import com.example.fluke.training.ui.later.ViewLaterActivity
import com.example.fluke.training.ui.myfav.ViewMyFavActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<ResultContractor.View, ResultPresenter>() {

    private var pager: Pager? = null

    override fun doInjection(appComponent: ApplicationComponent) = appComponent.inject(this)

    override fun layoutContentView(): Int = R.layout.activity_home

    override fun setupView() {
        applicationContext.getSharedPreferences(getString(R.string.session), Context.MODE_PRIVATE)
        applicationContext.getSharedPreferences("later", Context.MODE_PRIVATE)
        pager = Pager(supportFragmentManager)
        container.adapter = pager
        tabLayout?.setupWithViewPager(container)
//        btnViewMyFav.setOnClickListener {
//            startActivity(Intent(this , ViewMyFavActivity::class.java))
//        }
//        btnViewLater.setOnClickListener {
//            startActivity(Intent(this , ViewLaterActivity::class.java))
//        }
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
        const val tab4 = "Option"
    }
}